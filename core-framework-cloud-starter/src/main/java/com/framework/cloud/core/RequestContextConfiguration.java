package com.framework.cloud.core;

import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextListener;

/**
 * request
 *
 * @author wusiwei
 */
public class RequestContextConfiguration {

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

}
