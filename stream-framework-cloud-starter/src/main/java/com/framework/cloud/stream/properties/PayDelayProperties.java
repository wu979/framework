package com.framework.cloud.stream.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * 延迟队列配置
 *
 * @author wusiwei
 */
@Data
@ConfigurationProperties(prefix = "framework.rabbitmq")
public class PayDelayProperties {

    private Map<String, Long> delay;

    public Long delay(String channel) {
        Long delay = this.delay.get(channel);
        return null == delay || delay == 0L ? 300000 : delay;
    }
}
