package com.framework.cloud.executors.exception;

import java.io.Serializable;

/**
 * @author wusiwei
 */
public class ExecutorsException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -5418432811648726521L;

    private Integer code;

    private String msg;

    private ExecutorsException() {
    }

    public ExecutorsException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ExecutorsException(Integer code, String msg) {
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
