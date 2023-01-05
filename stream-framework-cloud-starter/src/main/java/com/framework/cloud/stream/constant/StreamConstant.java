package com.framework.cloud.stream.constant;

/**
 * @author wusiwei
 */
public class StreamConstant {

    public static final long TIMEOUT = 2000;

    public static final String HEADER_DELAY = "x-delay";

    public static final String RETRY = "rabbit:retry:%s";

    public static final long RETRY_TIME = 10;

    public static final String CONSUME = "rabbit:consume:%s";

    public static final long CONSUME_TIME = 30;

}
