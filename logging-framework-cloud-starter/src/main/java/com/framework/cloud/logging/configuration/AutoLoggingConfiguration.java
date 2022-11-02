package com.framework.cloud.logging.configuration;

import com.framework.cloud.logging.annotation.LogAspect;
import com.framework.cloud.logging.interceptor.TraceInterceptor;
import org.springframework.context.annotation.Bean;

/**
 *
 *
 * @author wusiwei
 */

public class AutoLoggingConfiguration {

    @Bean
    public LogAspect logAspect() {
        return new LogAspect();
    }

    @Bean
    public TraceInterceptor traceInterceptor() {
        return new TraceInterceptor();
    }
}
