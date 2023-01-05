package com.framework.cloud.stream.rabbit.listener;

import com.framework.cloud.stream.message.StreamMessage;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

import java.io.IOException;

/**
 * @author wusiwei
 */
@Slf4j
public abstract class MessageListener<T extends StreamMessage> extends AbstractListener<T> {

    @Override
    protected void receiveMessage(Message message, Channel channel) throws IOException {
        MessageProperties properties = message.getMessageProperties();
        String queue = properties.getConsumerQueue();
        String messageId = properties.getMessageId();
        boolean flag = false;
        try {
            //消息体
            T content = getContent(message);
            //未消费 消息该条消息
            if (canConsume(messageId)) {
                execute(message, content);
            }
            channel.basicAck(properties.getDeliveryTag(), Boolean.TRUE);
            flag = true;
        } finally {
            if (!flag) {
                try {
                    long deliveryTag = properties.getDeliveryTag();
                    if (dealFailAck(message)) {
                        channel.basicNack(deliveryTag, Boolean.FALSE, Boolean.TRUE);
                    } else {
                        fail(message, getContent(message));
                        channel.basicNack(deliveryTag, Boolean.FALSE, Boolean.FALSE);
                    }
                } catch (Exception e1) {
                    fail(message, getContent(message));
                    channel.basicNack(properties.getDeliveryTag(), Boolean.FALSE, Boolean.FALSE);
                }
            }
        }
    }

    /**
     * 业务执行方法
     */
    protected abstract void execute(Message message, T content);

    /**
     * 失败执行
     */
    protected abstract void fail(Message message, T content);
}
