package com.framework.cloud.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 *
 * @author wusiwei
 */
@Data
@ConfigurationProperties(prefix = "rest.client")
public class RestClientProperties {

    /**
     * 最大连接数
     */
    private int maxTotal = 1000;

    /**
     * 同路由最大并发数
     */
    private int maxPerRoute = 1000;

    /**
     * 重试次数
     */
    private int retryCount = 3;

    /**
     * 连接超时
     */
    private int connectTimeout = 12000;

    /**
     * 读取超时时间
     */
    private int readTimeout = 12000;

    /**
     * 连接不够用的等待时间，不宜过长，必须设置，比如连接不够用时，时间过长将是灾难性的
     */
    private int connectionRequestTimeout = 200;

    /**
     * 缓冲请求数据，默认值是true。通过POST或者PUT大量发送数据时，建议将此属性更改为false，以免耗尽内存。
     */
    private boolean bufferRequestBody = Boolean.FALSE;

}
