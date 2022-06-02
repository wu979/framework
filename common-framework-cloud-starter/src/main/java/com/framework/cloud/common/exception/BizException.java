package com.framework.cloud.common.exception;

import lombok.EqualsAndHashCode;

/**
 * 业务异常
 *
 * @author wusiwei
 */
@EqualsAndHashCode(callSuper = true)
public class BizException extends BaseException {
    private static final long serialVersionUID = -6581238957672428427L;

    public BizException(String msg) {
        super(msg);
    }

    public BizException(Integer code, String msg) {
        super(code, msg);
    }
}
