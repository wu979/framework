package com.framework.cloud.logging;

import com.framework.cloud.logging.annotation.LogAspect;
import com.framework.cloud.logging.annotation.TraceIdAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

/**
 * log auto configurator
 * @author wusiwei
 */
public class LoggingAutoConfiguration {

    @Bean
    @ConditionalOnProperty(value = "skywalking.traceId.header", havingValue = "true")
    public TraceIdAspect traceIdAspect() {
        return new TraceIdAspect();
    }

    @Bean
    public LogAspect logAspect() {
        return new LogAspect();
    }
}
