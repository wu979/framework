package com.framework.cloud.common.exception;

/**
 * 锁异常
 *
 * @author wusiwei
 */
public class LockException extends BaseException {
    private static final long serialVersionUID = 2812727976333573905L;

    public LockException(String msg) {
        super(msg);
    }

    public LockException(Integer code, String msg) {
        super(code, msg);
    }
}
