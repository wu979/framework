package com.framework.cloud.common.exception;

import java.io.Serializable;

/**
 * 全局异常
 *
 * @author wusiwei
 */
public class BaseException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 6176173237906378409L;

    private Integer code;

    private String msg;

    private BaseException() {
    }

    public BaseException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BaseException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
