package com.framework.cloud.common.base;

import com.framework.cloud.common.utils.UUIDUtil;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * spring cloud stream message body
 *
 * @author wusiwei
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class StreamMessage<T> extends BaseMessage implements Serializable {
    private static final long serialVersionUID = 3879923853064882303L;

    private T data;

    public StreamMessage() {
        super(UUIDUtil.uuid());
    }

    public StreamMessage(String messageId) {
        super(messageId);
    }

    public StreamMessage(T data) {
        super(UUIDUtil.uuid());
        this.data = data;
    }

    public StreamMessage(String messageId, T data) {
        super(messageId);
        this.data = data;
    }

    public static <T> StreamMessage<T> build(T data) {
        return new StreamMessage<T>(data);
    }

    public static <T> StreamMessage<T> build(String messageId, T data) {
        return new StreamMessage<T>(messageId, data);
    }
}
