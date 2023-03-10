package com.framework.cloud.cache.annotation;

import com.framework.cloud.cache.CacheAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * enable cache configuration with annotations
 *
 * @author wusiwei
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(CacheAutoConfiguration.class)
public @interface EnableCache {
}
