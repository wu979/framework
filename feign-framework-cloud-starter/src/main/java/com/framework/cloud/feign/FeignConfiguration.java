package com.framework.cloud.feign;

import com.alibaba.csp.sentinel.SphU;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.cloud.feign.converter.Jackson2HttpMessageConverter;
import com.framework.cloud.feign.sentinel.SentinelFeign;
import feign.Feign;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.loadbalancer.support.SimpleObjectProvider;
import org.springframework.cloud.openfeign.support.HttpMessageConverterCustomizer;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.http.converter.StringHttpMessageConverter;

import java.nio.charset.StandardCharsets;

/**
 * Feign 配置
 *
 * @author wusiwei
 */
@AllArgsConstructor
@AutoConfigureAfter(ObjectMapper.class)
public class FeignConfiguration {

    private final ObjectMapper objectMapper;

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    @Scope("prototype")
    @ConditionalOnClass({SphU.class, Feign.class})
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "feign.sentinel.enabled", havingValue = "true")
    @Primary
    public Feign.Builder feignSentinelBuilder() {
        return SentinelFeign.builder();
    }

    @Bean
    @ConditionalOnMissingBean
    public Decoder feignDecoder(ObjectFactory<HttpMessageConverters> httpMessageConverters,
                                ObjectProvider<HttpMessageConverterCustomizer> httpMessageConverterCustomizers) {
        return new ResponseEntityDecoder(new ResponseEntityDecoder(new SpringDecoder(httpMessageConverters, httpMessageConverterCustomizers)));
    }

    @Bean
    @ConditionalOnMissingBean
    public Encoder feignEncoder(ObjectFactory<HttpMessageConverters> httpMessageConverters) {
        return new SpringEncoder(httpMessageConverters);
    }

    @Bean
    public ObjectFactory<HttpMessageConverters> httpMessageConverters() {
        return new SimpleObjectProvider<>(new HttpMessageConverters(new StringHttpMessageConverter(StandardCharsets.UTF_8)));
    }

    @Bean
    public ObjectProvider<HttpMessageConverterCustomizer> httpMessageConverterCustomizers() {
        return new SimpleObjectProvider<>(new Jackson2HttpMessageConverter(objectMapper));
    }
}
