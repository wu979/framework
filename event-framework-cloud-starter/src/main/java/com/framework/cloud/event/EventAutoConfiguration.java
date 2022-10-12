package com.framework.cloud.event;

import com.framework.cloud.event.listener.ApplicationLocalListener;
import com.framework.cloud.event.publisher.ApplicationLocalPublisher;
import com.framework.cloud.event.publisher.ApplicationPostProcessor;
import org.springframework.context.annotation.Bean;

/**
 *
 *
 * @author wusiwei
 */
public class EventAutoConfiguration {

    @Bean
    public ApplicationPostProcessor applicationPostProcessor() {
        return new ApplicationPostProcessor();
    }

    @Bean
    public ApplicationLocalListener applicationLocalListener() {
        return new ApplicationLocalListener();
    }

    @Bean
    public ApplicationLocalPublisher applicationLocalPublisher() {
        return new ApplicationLocalPublisher();
    }

}
