package com.framework.cloud.stream.message;

import com.framework.cloud.common.utils.UUIDUtil;

/**
 * 消息体
 *
 * @author wusiwei
 */
public interface StreamMessage {

    /**
     * 消息ID
     */
    default String messageId() {
        return UUIDUtil.uuid();
    }

    /**
     * 是否是延时消息 默认False
     */
    default Boolean isDelay() {
        return false;
    };

    /**
     * 单个消息过期时间/延时时间(毫秒)
     */
    default long expiration() {
        return 0L;
    };
}
