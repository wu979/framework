package com.framework.cloud.common.exception;

import com.framework.cloud.common.enums.GlobalMessage;
import lombok.EqualsAndHashCode;

/**
 * @author wusiwei
 */
@EqualsAndHashCode(callSuper = true)
public class OauthException extends BaseException {
    private static final long serialVersionUID = 2662423967739016047L;

    public OauthException() {
        super(GlobalMessage.AUTHENTICATION_ERROR.getCode(), GlobalMessage.AUTHENTICATION_ERROR.getMsg());
    }

    public OauthException(String msg) {
        super(msg);
    }

    public OauthException(Integer code, String msg) {
        super(code, msg);
    }
}
