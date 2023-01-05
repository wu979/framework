package com.framework.cloud.common.exception;

import lombok.EqualsAndHashCode;

/**
 * @author wusiwei
 */
@EqualsAndHashCode(callSuper = true)
public class RabbitException extends BaseException {
    private static final long serialVersionUID = -1341662894587221499L;

    public RabbitException(String msg) {
        super(msg);
    }

    public RabbitException(Integer code, String msg) {
        super(code, msg);
    }
}
