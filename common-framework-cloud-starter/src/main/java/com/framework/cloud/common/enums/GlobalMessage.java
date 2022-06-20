package com.framework.cloud.common.enums;

import lombok.AllArgsConstructor;

/**
 * 消息枚举
 *
 * @author wusiwei
 */
@AllArgsConstructor
public enum GlobalMessage {

    /**
     * 全局统一消息
     */
    ERROR(500, "系统异常!"),
    SUCCESS(200, "操作成功!"),
    FAIL(0, "操作失败!"),
    ;

    Integer code;
    String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
