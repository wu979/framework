package com.framework.cloud.mybatis.enums;

import lombok.AllArgsConstructor;

/**
 * Mybatis消息 枚举
 *
 * @author wusiwei
 */
@AllArgsConstructor
public enum MybatisMessageEnum {

    /** 全局统一消息 */
    NOT_FOUND(404, "数据不见了!"),
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
