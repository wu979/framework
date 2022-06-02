package com.framework.cloud.common.exception;

/**
 * 枚举异常
 *
 * @author wusiwei
 */
public class EnumException extends BaseException {
    private static final long serialVersionUID = -858419970603167838L;

    public EnumException(String msg) {
        super(msg);
    }

    public EnumException(Integer code, String msg) {
        super(code, msg);
    }
}
