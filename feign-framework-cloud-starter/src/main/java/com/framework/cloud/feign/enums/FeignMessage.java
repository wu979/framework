package com.framework.cloud.feign.enums;

import com.framework.cloud.common.enums.GlobalMessage;
import lombok.AllArgsConstructor;

/**
 * Feign调用异常
 *
 * @author wusiwei
 */
@AllArgsConstructor
public enum FeignMessage {

    /**
     * 异常消息
     */
    RETRYABLE(-1, "{0}应用超时了"),
    TIME_OUT(502, "{0}应用超时了"),
    NOT_FOUND(503, "{0}应用不见了"),
    ;

    Integer code;
    String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static String getMsg(Integer code) {
        for (FeignMessage messageEnum : FeignMessage.values()) {
            if (messageEnum.getCode().equals(code)) {
                return messageEnum.getMsg();
            }
        }
        return GlobalMessage.ERROR.getMsg();
    }
}
