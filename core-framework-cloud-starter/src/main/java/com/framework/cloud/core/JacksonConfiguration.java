package com.framework.cloud.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.cloud.core.converter.JacksonHttpMessageConverter;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Http消息序列化
 *
 * @author wusiwei
 */
@AllArgsConstructor
@AutoConfigureAfter(ObjectMapper.class)
public class JacksonConfiguration implements WebMvcConfigurer {

    private final ObjectMapper objectMapper;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.clear();
        converters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
        converters.add(new JacksonHttpMessageConverter(objectMapper).converter());
    }
}
