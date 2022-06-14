package com.framework.cloud.feign;

import com.alibaba.csp.sentinel.SphU;
import com.framework.cloud.feign.sentinel.SentinelFeign;
import feign.Feign;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.HttpMessageConverterCustomizer;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

/**
 * Feign 配置
 *
 * @author wusiwei
 */
@AllArgsConstructor
public class FeignConfiguration {

    private final ObjectProvider<HttpMessageConverterCustomizer> httpMessageConverterCustomizers;
    private final ObjectFactory<HttpMessageConverters> messageConverters;

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
    public Decoder feignDecoder() {
        return new ResponseEntityDecoder(new ResponseEntityDecoder(new SpringDecoder(this.messageConverters, this.httpMessageConverterCustomizers)));
    }

    @Bean
    @ConditionalOnMissingBean
    public Encoder feignEncoder() {
        return new SpringEncoder(this.messageConverters);
    }
}
