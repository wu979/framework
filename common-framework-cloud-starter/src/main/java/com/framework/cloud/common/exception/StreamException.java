package com.framework.cloud.common.exception;

import lombok.EqualsAndHashCode;

/**
 * @author wusiwei
 */
@EqualsAndHashCode(callSuper = true)
public class StreamException extends BaseException {
    private static final long serialVersionUID = -1341662894587221499L;

    public StreamException(String msg) {
        super(msg);
    }

    public StreamException(Integer code, String msg) {
        super(code, msg);
    }
}
