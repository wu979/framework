package com.framework.cloud.seata;

import com.alibaba.cloud.seata.rest.SeataRestTemplateAutoConfiguration;
import com.alibaba.cloud.seata.rest.SeataRestTemplateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author wusiwei
 */
@Configuration
@SuppressWarnings("all")
@EnableAutoConfiguration(exclude = SeataRestTemplateAutoConfiguration.class)
public class SeataAutoConfiguration implements BeanPostProcessor {

    @Autowired(required = false)
    private Collection<RestTemplate> restTemplates;

    @PostConstruct
    public void init() {
        if (this.restTemplates != null) {
            for (RestTemplate restTemplate : restTemplates) {
                List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>(
                        restTemplate.getInterceptors());
                interceptors.add(new SeataRestTemplateInterceptor());
                restTemplate.setInterceptors(interceptors);
            }
        }
    }


}
