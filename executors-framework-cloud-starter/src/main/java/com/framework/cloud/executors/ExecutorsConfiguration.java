package com.framework.cloud.executors;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 线程池配置
 *
 * @author wusiwei
 */
@ConditionalOnProperty(prefix = "swagger", value = "enabled", havingValue = "true")
@EnableAsync(proxyTargetClass = true)
public class ExecutorsConfiguration {
}
