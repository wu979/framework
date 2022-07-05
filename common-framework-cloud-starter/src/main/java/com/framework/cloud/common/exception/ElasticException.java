package com.framework.cloud.common.exception;

/**
 * @author wusiwei
 */
public class ElasticException extends BaseException {
    private static final long serialVersionUID = 5673256344918371112L;

    public ElasticException(String msg) {
        super(msg);
    }

    public ElasticException(Integer code, String msg) {
        super(code, msg);
    }
}
