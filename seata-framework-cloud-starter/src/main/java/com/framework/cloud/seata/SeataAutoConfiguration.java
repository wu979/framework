package com.framework.cloud.seata;

import io.seata.core.context.RootContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wusiwei
 */
@Configuration
@SuppressWarnings("all")
public class SeataAutoConfiguration implements BeanPostProcessor {

    //@Autowired(required = false)
    //private Collection<RestTemplate> restTemplates;
    //
    //@PostConstruct
    //public void init() {
    //    if (this.restTemplates != null) {
    //        for (RestTemplate restTemplate : restTemplates) {
    //            List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>(
    //                    restTemplate.getInterceptors());
    //            interceptors.add(new SeataRestTemplateInterceptor());
    //            restTemplate.setInterceptors(interceptors);
    //        }
    //    }
    //}

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof RestTemplate) {
            RestTemplate restTemplate = (RestTemplate) bean;
            List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>(
                    restTemplate.getInterceptors());
            interceptors.add(new SeataRestTemplateInterceptor());
            restTemplate.setInterceptors(interceptors);
        }
        return bean;
    }

    static class SeataRestTemplateInterceptor implements ClientHttpRequestInterceptor {
        @Override
        public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes,
                                            ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
            HttpRequestWrapper requestWrapper = new HttpRequestWrapper(httpRequest);
            String xid = RootContext.getXID();
            if (!StringUtils.isEmpty(xid)) {
                requestWrapper.getHeaders().add(RootContext.KEY_XID, xid);
            }
            return clientHttpRequestExecution.execute(requestWrapper, bytes);
        }
    }
}
