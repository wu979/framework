package com.framework.cloud.stream;

import com.framework.cloud.stream.annotation.StreamAspect;
import com.framework.cloud.stream.configuration.RabbitStreamConfiguration;
import com.framework.cloud.stream.wrapper.StreamListenerChannelInterceptor;
import com.framework.cloud.stream.wrapper.StreamListenerHandlerMethodFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.integration.config.GlobalChannelInterceptor;
import org.springframework.messaging.support.ChannelInterceptor;

/**
 * domain driven event initialization configuration
 *
 * @author wusiwei
 */
@Import({RabbitStreamConfiguration.class, StreamListenerHandlerMethodFactoryPostProcessor.class})
public class StreamAutoConfiguration {

    @Bean
    @GlobalChannelInterceptor
    public ChannelInterceptor streamListenerChannelInterceptor() {
        return new StreamListenerChannelInterceptor();
    }

    @Bean
    public StreamAspect streamAspect() {
        return new StreamAspect();
    }
}

