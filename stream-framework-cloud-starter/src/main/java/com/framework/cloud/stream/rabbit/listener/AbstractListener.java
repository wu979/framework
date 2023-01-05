package com.framework.cloud.stream.rabbit.listener;

import com.alibaba.fastjson2.JSON;
import com.framework.cloud.common.exception.RabbitException;
import com.framework.cloud.stream.constant.StreamConstant;
import com.framework.cloud.stream.message.StreamMessage;
import com.framework.cloud.stream.properties.StreamProperties;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.SerializerMessageConverter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * default abstract listener, responsible for processing message public methods
 *
 * @author wusiwei
 */
@Slf4j
@EnableConfigurationProperties(StreamProperties.class)
public abstract class AbstractListener<T extends StreamMessage> extends StreamConstant {

    protected static final SerializerMessageConverter SERIALIZER_MESSAGE_CONVERTER = new SerializerMessageConverter();
    protected static final String ENCODING = Charset.defaultCharset().name();

    @Resource
    protected RedisTemplate<String, String> redisTemplate;

    @Resource
    protected ValueOperations<String, Long> numberOperations;

    @Resource
    protected StreamProperties streamProperties;

    /**
     * pre message proxy
     */
    protected abstract void receiveMessage(Message message, Channel channel) throws IOException;

    /**
     * fail ACK
     */
    protected boolean dealFailAck(Message message) throws IOException {
        if (!streamProperties.isEnableRetry()) {
            return false;
        }
        long retryCount = streamProperties.getRetryCount();
        if (retryCount < 1) {
            return false;
        }
        MessageProperties properties = message.getMessageProperties();
        String queue = properties.getConsumerQueue();
        String messageId = properties.getMessageId();
        String retryKey = String.format(RETRY, queue, messageId);
        Long retryCounted = numberOperations.increment(retryKey, 1);
        redisTemplate.expire(retryKey, RETRY_TIME, TimeUnit.MINUTES);
        if (Objects.equals(retryCounted, retryCount)) {
            return false;
        }
        //删除消费者Key 保证重试
        redisTemplate.delete(getConsumeKey(messageId));
        return true;
    }

    /**
     * 获取消息体
     */
    protected T getContent(Message message) {
        String body = getBodyContentAsString(message);
        Class<T> contentClass = null;
        try {
            ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
            contentClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
        } catch (Exception e) {
            throw new RabbitException(com.framework.cloud.stream.enums.StreamMessage.MQ_T.getMsg());
        }

        if (contentClass != null && contentClass.getName().equals(StreamMessage.class.getCanonicalName())) {
            throw new RabbitException(com.framework.cloud.stream.enums.StreamMessage.MQ_TYPE.getMsg());
        }
        return JSON.parseObject(body, contentClass);
    }

    /**
     * 是否能消费，防止重复消费
     */
    protected boolean canConsume(String messageId) {
        String consumeKey = getConsumeKey(messageId);
        Boolean lock = redisTemplate.opsForValue().setIfAbsent(consumeKey, messageId, CONSUME_TIME, TimeUnit.MINUTES);
        return Boolean.TRUE.equals(lock);
    }

    /**
     * 消费者Key
     */
    protected String getConsumeKey(String messageId) {
        return String.format(CONSUME, messageId);
    }

    /**
     * 转换body
     */
    protected String getBodyContentAsString(Message message) {
        if (message.getBody() == null) {
            return null;
        }
        try {
            MessageProperties properties = message.getMessageProperties();
            String contentType = (properties != null) ? properties.getContentType() : null;
            if (MessageProperties.CONTENT_TYPE_SERIALIZED_OBJECT.equals(contentType)) {
                return SERIALIZER_MESSAGE_CONVERTER.fromMessage(message).toString();
            }
            if (MessageProperties.CONTENT_TYPE_TEXT_PLAIN.equals(contentType)
                    || MessageProperties.CONTENT_TYPE_JSON.equals(contentType)
                    || MessageProperties.CONTENT_TYPE_JSON_ALT.equals(contentType)
                    || MessageProperties.CONTENT_TYPE_XML.equals(contentType)) {
                return new String(message.getBody(), ENCODING);
            }
        } catch (Exception e) {
            log.error("Rabbit MQ Content Type Error");
        }
        return message.getBody().toString() + "(byte[" + message.getBody().length + "])";
    }
}
