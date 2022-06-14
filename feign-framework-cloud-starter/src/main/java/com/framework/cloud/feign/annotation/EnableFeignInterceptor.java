package com.framework.cloud.feign.annotation;

import com.framework.cloud.feign.FeignContextConfiguration;
import com.framework.cloud.feign.interceptor.FeignAuthInterceptor;
import com.framework.cloud.feign.interceptor.FeignHttpInterceptor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Feign 拦截器 传递数据给下游服务，包含权限数据和http的相关数据
 *
 * @author wusiwei
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({FeignContextConfiguration.class, FeignAuthInterceptor.class, FeignHttpInterceptor.class})
public @interface EnableFeignInterceptor {
}
