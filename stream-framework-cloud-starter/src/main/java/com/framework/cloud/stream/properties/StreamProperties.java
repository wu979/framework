package com.framework.cloud.stream.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @author wusiwei
 */
@Data
@ConfigurationProperties(prefix = "framework.stream")
public class StreamProperties {

    /**
     * 是否开启重试
     */
    private boolean enableRetry = true;

    /**
     * 重试次数
     */
    private long retryCount = 3;

    /**
     * 延迟消息 过期时间
     * Map<队列名称,时间>
     */
    private Map<String, Long> delay;

    public Long delay(String channel) {
        Long delay = this.delay.get(channel);
        return null == delay || delay == 0L ? 300000 : delay;
    }
}
