package com.framework.cloud.stream.rabbit.listener;

import com.alibaba.fastjson2.TypeReference;
import com.framework.cloud.common.base.BaseMessage;
import com.framework.cloud.common.base.RabbitMessage;
import com.framework.cloud.common.exception.StreamException;
import com.framework.cloud.common.utils.FastJsonUtil;
import com.framework.cloud.stream.constant.StreamConstant;
import com.framework.cloud.stream.enums.ErrorMessage;
import com.framework.cloud.stream.enums.PersistenceType;
import com.framework.cloud.stream.properties.StreamProperties;
import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
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

import static org.springframework.amqp.core.MessageProperties.*;

/**
 * default abstract listener, responsible for processing message public methods
 *
 * @author wusiwei
 */
@Slf4j
@SuppressWarnings("all")
@EnableConfigurationProperties(StreamProperties.class)
public abstract class AbstractListener<T> extends StreamConstant implements RabbitListener<T> {

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
    protected abstract void receiveMessage(Message message, Channel channel);

    /**
     * fail
     */
    @Override
    public void fail(Message message, RabbitMessage<T> rabbitMessage) {
        fail(PersistenceType.DATASOURCE, message, rabbitMessage);
    }

    /**
     * fail
     */
    @Override
    public final void fail(PersistenceType persistenceType, Message message, RabbitMessage<T> rabbitMessage) {
        if (PersistenceType.DATASOURCE.equals(persistenceType)) {
            persistenceDataSource(message, rabbitMessage);
        } else {
            persistenceCache(message, rabbitMessage);
        }
    }

    /**
     * 持久化-数据库
     */
    protected final void persistenceDataSource(Message message, RabbitMessage<T> rabbitMessage) {

    }

    /**
     * 持久化-缓存
     */
    protected final void persistenceCache(Message message, RabbitMessage<T> rabbitMessage) {

    }

    /**
     * fail ACK
     */
    protected final boolean dealFailAck(Message message) throws IOException {
        if (!streamProperties.isEnableRetry()) {
            return false;
        }
        long retryCount = streamProperties.getRetryCount();
        if (retryCount < 1) {
            return false;
        }
        MessageProperties properties = message.getMessageProperties();
        String messageId = properties.getMessageId();
        String retryKey = String.format(RETRY, messageId);
        Long retryCounted = numberOperations.increment(retryKey, 1);
        redisTemplate.expire(retryKey, RETRY_TIME, TimeUnit.MINUTES);
        if (Objects.equals(retryCounted, retryCount)) {
            return false;
        }
        redisTemplate.delete(getConsumeKey(messageId));
        return true;
    }

    /**
     * 是否能消费，防止重复消费
     */
    protected final boolean canConsume(String messageId) {
        String consumeKey = getConsumeKey(messageId);
        Boolean lock = redisTemplate.opsForValue().setIfAbsent(consumeKey, messageId, CONSUME_TIME, TimeUnit.MINUTES);
        return Boolean.TRUE.equals(lock);
    }

    /**
     * 获取消息体
     */
    protected final RabbitMessage<T> getContent(Message message) {
        RabbitMessage<T> rabbitMessage = null;
        try {
            String body = getBodyContentAsString(message);
            if (null == body) {
                return null;
            }
            ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
            Class<T> messageType = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
            rabbitMessage = FastJsonUtil.toJavaObject(body, new TypeReference<RabbitMessage<T>>(messageType) {});
        } catch (Exception e) {
            throw new StreamException(ErrorMessage.MQ_T.getMsg());
        }
        if (rabbitMessage != null && rabbitMessage.getClass().getName().equals(BaseMessage.class.getCanonicalName())) {
            throw new StreamException(ErrorMessage.MQ_TYPE.getMsg());
        }
        return rabbitMessage;
    }

    /**
     * 消费者Key
     */
    protected final String getConsumeKey(String messageId) {
        return String.format(CONSUME, messageId);
    }

    /**
     * 转换body
     */
    @SneakyThrows
    protected final String getBodyContentAsString(Message message) {
        if (message.getBody() == null) {
            return null;
        }
        MessageProperties properties = message.getMessageProperties();
        String contentType = (properties != null) ? properties.getContentType() : null;
        switch (contentType) {
            case CONTENT_TYPE_SERIALIZED_OBJECT:
                return SERIALIZER_MESSAGE_CONVERTER.fromMessage(message).toString();
            case CONTENT_TYPE_TEXT_PLAIN:
            case CONTENT_TYPE_JSON:
            case CONTENT_TYPE_JSON_ALT:
            case CONTENT_TYPE_XML:
                return new String(message.getBody(), ENCODING);
            default:
                return message.getBody().toString() + "(byte[" + message.getBody().length + "])";
        }
    }
}
