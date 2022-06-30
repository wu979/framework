package com.framework.cloud.core;

import com.framework.cloud.core.event.ApplicationPostProcessor;
import org.springframework.context.annotation.Bean;

/**
 * @author wusiwei
 */
public class ApplicationConfiguration {

    @Bean
    public ApplicationPostProcessor applicationPostProcessor() {
        return new ApplicationPostProcessor();
    }
}
