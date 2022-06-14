package com.framework.cloud.core;

import com.framework.cloud.core.properties.RestClientProperties;
import lombok.AllArgsConstructor;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

/**
 * Http链接配置
 *
 * @author wusiwei
 */
@AllArgsConstructor
@ConditionalOnProperty(prefix = "rest.client", value = "enable", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(RestClientProperties.class)
public class RestTemplateConfiguration {

    private final RestClientProperties restClientProperties;

    /**
     * 负载
     */
    @Bean("restTemplate")
    @LoadBalanced
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(charset(restTemplate));
        restTemplate.setRequestFactory(getHttpClientFactory());
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        restTemplate.setInterceptors(Collections.singletonList(new RestTemplateInterceptor()));
        return restTemplate;
    }

    @SuppressWarnings("all")
    static class RestTemplateInterceptor implements ClientHttpRequestInterceptor {
        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
            HttpServletRequest httpRequest = attributes.getRequest();
            return execution.execute(request, body);
        }
    }

    /**
     * 连接工厂
     */
    private HttpComponentsClientHttpRequestFactory getHttpClientFactory() {
        DefaultHttpRequestRetryHandler retryHandler = new DefaultHttpRequestRetryHandler(restClientProperties.getRetryCount(), Boolean.TRUE);
        PoolingHttpClientConnectionManager poolingHttpClient = getPoolingHttpClient(restClientProperties);
        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setConnectionManager(poolingHttpClient);
        httpClientBuilder.setRetryHandler(retryHandler);
        HttpClient httpClient = httpClientBuilder.build();
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        //连接超时
        clientHttpRequestFactory.setConnectTimeout(restClientProperties.getConnectTimeout());
        //数据读取超时时间，即SocketTimeout
        clientHttpRequestFactory.setReadTimeout(restClientProperties.getReadTimeout());
        //连接不够用的等待时间，不宜过长，必须设置，比如连接不够用时，时间过长将是灾难性的
        clientHttpRequestFactory.setConnectionRequestTimeout(restClientProperties.getConnectionRequestTimeout());
        //缓冲请求数据，默认值是true。通过POST或者PUT大量发送数据时，建议将此属性更改为false，以免耗尽内存。
        clientHttpRequestFactory.setBufferRequestBody(Boolean.FALSE);
        return clientHttpRequestFactory;
    }

    /**
     * 长连接
     */
    private PoolingHttpClientConnectionManager getPoolingHttpClient(RestClientProperties restClientProperties) {
        //长连接
        PoolingHttpClientConnectionManager pollingConnectionManager = new PoolingHttpClientConnectionManager();
        //总连接数
        pollingConnectionManager.setMaxTotal(restClientProperties.getMaxTotal());
        //同路由的并发数
        pollingConnectionManager.setDefaultMaxPerRoute(restClientProperties.getMaxPerRoute());
        return pollingConnectionManager;
    }

    /**
     * 修改默认的字符集类型为utf-8
     */
    private List<HttpMessageConverter<?>> charset(RestTemplate restTemplate) {
        List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();
        HttpMessageConverter<?> converterTarget = null;
        for (HttpMessageConverter<?> item : converterList) {
            if (StringHttpMessageConverter.class == item.getClass()) {
                converterTarget = item;
                break;
            }
        }
        if (null != converterTarget) {
            converterList.remove(converterTarget);
        }
        converterList.add(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return converterList;
    }
}
