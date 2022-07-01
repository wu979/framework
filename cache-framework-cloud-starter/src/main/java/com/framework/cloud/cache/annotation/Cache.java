package com.framework.cloud.cache.annotation;

import com.framework.cloud.cache.enums.CacheMedium;
import com.framework.cloud.cache.enums.CacheType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * distributed L2 cache, implementation based on redis and caffeine
 *
 * @author wusiwei
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {

    String key();

    /**
     * 缓存类型 （读、写、删、读写）
     */
    CacheType type();

    /**
     * 缓存介质 （分布式、本地、全部）
     */
    CacheMedium medium() default CacheMedium.DISTRIBUTED;

    /**
     * 过期时间
     * 本地缓存获取配置文件默认时间进行缓存 过期时间只会对分布式例如Redis生效
     * default 0 分布式缓存取配置文件默认时间
     */
    long timeout() default 0;

    /**
     * 过期时间单位
     */
    TimeUnit unit() default TimeUnit.MINUTES;

}
