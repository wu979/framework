package com.framework.cloud.stream;

import com.framework.cloud.stream.configuration.RabbitStreamConfiguration;
import com.framework.cloud.stream.interceptor.StreamMqTraceIdChannelInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.integration.config.GlobalChannelInterceptor;

/**
 * domain driven event initialization configuration
 *
 * @author wusiwei
 */
@Import(RabbitStreamConfiguration.class)
public class StreamAutoConfiguration {

    @Bean
    @GlobalChannelInterceptor
    public StreamMqTraceIdChannelInterceptor streamMqTraceIdChannelInterceptor() {
        return new StreamMqTraceIdChannelInterceptor();
    }

}
