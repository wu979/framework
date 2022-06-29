package com.framework.cloud.feign.interceptor;

import cn.hutool.core.util.ObjectUtil;
import feign.Headers;
import feign.MethodMetadata;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

/**
 * @program: wsw-starter-cloud
 * @description: Feign 拦截器传递数据给下游服务 包含http的相关数据
 * @author: wsw
 * @create: 2021-09-28 17:52
 **/
public class FeignHttpInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        if (attributes == null) {
            return;
        }
        MethodMetadata methodMetadata = requestTemplate.methodMetadata();
        Method method = methodMetadata.method();
        Headers annotation = method.getAnnotation(Headers.class);
        if (ObjectUtil.isNull(annotation)) {
            requestTemplate.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        }
    }
}
