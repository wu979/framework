package com.framework.cloud.core;

import com.framework.cloud.core.event.ApplicationPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wusiwei
 */
@Configuration
public class ApplicationConfiguration {

    @Bean
    public ApplicationPostProcessor applicationPostProcessor() {
        return new ApplicationPostProcessor();
    }
}
