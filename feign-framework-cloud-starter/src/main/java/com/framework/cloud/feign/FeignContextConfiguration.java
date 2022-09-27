package com.framework.cloud.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.cloud.feign.filter.SeataFilter;
import com.framework.cloud.feign.filter.TenantFilter;
import com.framework.cloud.feign.filter.UserFilter;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * application context filter configuration initializing
 *
 * @author wusiwei
 */
@AllArgsConstructor
@SuppressWarnings({"rawtypes", "unchecked"})
@AutoConfigureAfter(FeignConfiguration.class)
@ConditionalOnWebApplication
public class FeignContextConfiguration {

    private final ObjectMapper objectMapper;

    @Bean("tenantFilter")
    public FilterRegistrationBean tenantFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new TenantFilter(objectMapper));
        registration.addUrlPatterns("/*");
        return registration;
    }

    @Bean("userFilter")
    public FilterRegistrationBean userFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new UserFilter(objectMapper));
        registration.addUrlPatterns("/*");
        return registration;
    }

    @Bean("seataFilter")
    public FilterRegistrationBean seataFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new SeataFilter());
        registration.addUrlPatterns("/*");
        return registration;
    }

}
