package com.framework.cloud.common.base;

import lombok.Getter;

import java.io.Serializable;

/**
 * 消息体
 *
 * @author wusiwei
 */
public abstract class BaseMessage implements Serializable {
    private static final long serialVersionUID = 3573720672952038837L;

    /**
     * 消息ID
     */
    @Getter
    private final String messageId;

    public BaseMessage(String messageId) {
        this.messageId = messageId;
    }
}
