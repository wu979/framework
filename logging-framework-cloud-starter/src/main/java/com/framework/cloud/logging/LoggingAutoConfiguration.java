package com.framework.cloud.logging;

import com.framework.cloud.logging.annotation.LogAspect;
import com.framework.cloud.logging.filter.TraceFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * log auto configurator
 * @author wusiwei
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class LoggingAutoConfiguration {

    @Bean
    public LogAspect logAspect() {
        return new LogAspect();
    }

    @Bean
    public FilterRegistrationBean traceInterceptor() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new TraceFilter());
        registration.addUrlPatterns("/*");
        return registration;
    }
}
