package com.framework.cloud.stream;

import com.framework.cloud.stream.configuration.RabbitStreamConfiguration;
import com.framework.cloud.stream.extend.StreamChannelInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.integration.config.GlobalChannelInterceptor;
import org.springframework.messaging.support.ChannelInterceptor;

/**
 * domain driven event initialization configuration
 *
 * @author wusiwei
 */
@Import({RabbitStreamConfiguration.class, MessageHandlerMethodFactoryPostProcessor.class})
public class StreamAutoConfiguration {

    @Bean
    @GlobalChannelInterceptor
    public ChannelInterceptor streamMqChannelInterceptor() {
        return new StreamChannelInterceptor();
    }

}

