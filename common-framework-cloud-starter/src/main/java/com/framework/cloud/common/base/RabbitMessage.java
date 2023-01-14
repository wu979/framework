package com.framework.cloud.common.base;

import com.framework.cloud.common.utils.UUIDUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * rabbit mq message body
 *
 * @author wusiwei
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class RabbitMessage<T> extends BaseMessage implements Serializable {
    private static final long serialVersionUID = -3966022542085191597L;

    private T data;

    /**
     * 是否是延时消息 默认False
     */
    private boolean isDelay = false;

    /**
     * 单个消息过期时间/延时时间(毫秒)
     */
    private long expiration = 0;

    public RabbitMessage() {
        super(UUIDUtil.uuid());
    }

    public RabbitMessage(String messageId) {
        super(messageId);
    }

    public RabbitMessage(String messageId, T data) {
        super(messageId);
        this.data = data;
    }

    public RabbitMessage(String messageId, T data, boolean isDelay) {
        super(messageId);
        this.data = data;
        this.isDelay = isDelay;
    }

    public RabbitMessage(T data, boolean isDelay, long expiration) {
        super(UUIDUtil.uuid());
        this.data = data;
        this.isDelay = isDelay;
        this.expiration = expiration;
    }

    public RabbitMessage(String messageId, T data, boolean isDelay, long expiration) {
        super(messageId);
        this.data = data;
        this.isDelay = isDelay;
        this.expiration = expiration;
    }

    public static <T> RabbitMessage<T> build(T data) {
        return new RabbitMessage<T>().setData(data);
    }

    public static <T> RabbitMessage<T> build(String messageId, T data) {
        return new RabbitMessage<T>(messageId).setData(data);
    }

    public static <T> RabbitMessage<T> build(long expiration, T data) {
        return new RabbitMessage<T>().setData(data).setDelay(true).setExpiration(expiration);
    }

    public static <T> RabbitMessage<T> build(String messageId, long expiration, T data) {
        return new RabbitMessage<T>(messageId).setDelay(true).setExpiration(expiration).setData(data);
    }
}
