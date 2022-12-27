package com.framework.cloud.stream.model;

import com.framework.cloud.common.utils.UUIDUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

/**
 * 消息体
 *
 * @author wusiwei
 */
@Data
public class RabbitMessage implements Serializable {
    private static final long serialVersionUID = -1308015676562436320L;

    /**
     * 消息ID
     */
    protected String messageId = UUIDUtil.uuid();

    /**
     * 是否是延时消息 默认False
     */
    protected Boolean isDelay = Boolean.FALSE;

    /**
     * 单个消息过期时间/延时时间(毫秒)
     */
    protected Long expiration;

}
