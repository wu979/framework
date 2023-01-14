package com.framework.cloud.stream.enums;

import lombok.AllArgsConstructor;

/**
 * 事件消息 枚举
 *
 * @author wusiwei
 */
@AllArgsConstructor
public enum ErrorMessage {

    MQ_T("消息通道异常,缺失泛型"),
    MQ_BODY_NULL("消息体为空"),
    MQ_TYPE("消息通道异常,请指定相应的消息类型"),

    ;
    String msg;

    public String getMsg() {
        return msg;
    }
}
