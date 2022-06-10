package com.framework.cloud.mybatis.exception;

import java.io.Serializable;

/**
 * 未找到异常类型
 *
 * @author wusiwei
 */
public class NotFoundException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -2651477552881003042L;

    private Integer code;

    private String msg;

    private NotFoundException() {}

    public NotFoundException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public NotFoundException(Integer code, String msg) {
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
