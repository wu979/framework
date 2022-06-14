package com.framework.cloud.feign.annotation;

import com.framework.cloud.feign.FeignConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * openfeign启动器
 *
 * @author wusiwei
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({FeignConfiguration.class})
public @interface EnableFeign {
}
