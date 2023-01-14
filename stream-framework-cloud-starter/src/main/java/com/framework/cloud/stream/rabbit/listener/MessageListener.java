package com.framework.cloud.stream.rabbit.listener;

import com.framework.cloud.common.base.RabbitMessage;
import com.framework.cloud.common.exception.BaseException;
import com.framework.cloud.common.utils.FastJsonUtil;
import com.framework.cloud.stream.constant.StreamConstant;
import com.framework.cloud.stream.utils.MessageHeaderUtil;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.listener.adapter.MessagingMessageListenerAdapter;
import org.springframework.scheduling.annotation.Async;

/**
 * @author wusiwei
 */
@Slf4j
public abstract class MessageListener<T> extends AbstractListener<T> {

    /**
     * 接受消息 由子类Listener调用
     * 异步的原因 是 {@link MessageListener#consume } 消费时，保证消费事务正常回滚
     * 如不开启异步 抛出的异常会返回 {@link MessagingMessageListenerAdapter#onMessage(Message, Channel)}
     * MQ认为是invoke时解析失败 两者定义不同 其实应该是消费失败 对于消费失败 会调用 {@link MessageListener#basicNack }
     */
    @Async(StreamConstant.CONSUME_POOL)
    @Override
    protected void receiveMessage(Message message, Channel channel) {
        MessageProperties properties = message.getMessageProperties();
        RabbitMessage<T> rabbitMessage = getContent(message);
        boolean flag = false;
        try {
            if (null != rabbitMessage) {
                //上下文
                MessageHeaderUtil.doPostHeaders(properties.getHeaders());
                //未消费 消息该条消息
                if (canConsume(properties.getMessageId())) {
                    consume(message, rabbitMessage);
                }
                flag = true;
            }
        } finally {
            basicNack(flag, message, channel, rabbitMessage);
        }
    }

    private void consume(Message message, RabbitMessage<T> rabbitMessage) {
        MessageProperties properties = message.getMessageProperties();
        try {
            consumeMessage(message, rabbitMessage);
        } catch (BaseException e) {
            log.error(CONSUME_FORMAT, "BUSINESS", properties.getMessageId(), FastJsonUtil.toJSONString(rabbitMessage), e);
            throw e;
        } catch (Exception e) {
            log.error(CONSUME_FORMAT, "ERROR", properties.getMessageId(), FastJsonUtil.toJSONString(rabbitMessage), e);
            throw e;
        }
    }

    private void basicNack(boolean flag, Message message, Channel channel, RabbitMessage<T> rabbitMessage) {
        MessageProperties properties = message.getMessageProperties();
        try {
            if (flag) {
                channel.basicAck(properties.getDeliveryTag(), Boolean.TRUE);
            } else {
                long deliveryTag = properties.getDeliveryTag();
                if (dealFailAck(message)) {
                    channel.basicNack(deliveryTag, Boolean.FALSE, Boolean.TRUE);
                } else {
                    if (null != rabbitMessage) {
                        fail(message, rabbitMessage);
                    }
                    channel.basicNack(deliveryTag, Boolean.FALSE, Boolean.FALSE);
                }
            }
        } catch (Exception e) {
            try {
                if (null != rabbitMessage) {
                    fail(message, rabbitMessage);
                }
                channel.basicNack(properties.getDeliveryTag(), Boolean.FALSE, Boolean.FALSE);
            } catch (Exception e1) {
                log.error(StreamConstant.CONSUME_FORMAT, "ERROR", properties.getMessageId(), FastJsonUtil.toJSONString(rabbitMessage), e1);
            }
        }
    }
}

