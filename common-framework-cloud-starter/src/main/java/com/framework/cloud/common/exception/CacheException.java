package com.framework.cloud.common.exception;

import lombok.EqualsAndHashCode;

/**
 * @author wusiwei
 */
@EqualsAndHashCode(callSuper = true)
public class CacheException extends BaseException {
    private static final long serialVersionUID = -8865828305365772068L;

    public CacheException(String msg) {
        super(msg);
    }

    public CacheException(Integer code, String msg) {
        super(code, msg);
    }
}
