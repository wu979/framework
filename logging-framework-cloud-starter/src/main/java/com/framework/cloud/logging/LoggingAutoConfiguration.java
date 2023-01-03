package com.framework.cloud.logging;

import com.framework.cloud.logging.annotation.LogAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

/**
 * log auto configurator
 * @author wusiwei
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class LoggingAutoConfiguration {

    @Bean
    @ConditionalOnProperty(value = "skywalking.traceId.header", havingValue = "true")
    public LogAspect logAspect() {
        return new LogAspect();
    }

}
