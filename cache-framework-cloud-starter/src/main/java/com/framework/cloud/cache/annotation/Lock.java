package com.framework.cloud.cache.annotation;

import com.framework.cloud.cache.enums.CacheTypeEnum;
import com.framework.cloud.cache.enums.LockTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁 注解 用于方法上 通过切面代理
 *
 * @author wusiwei
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Lock {

    /**
     * 锁键
     */
    String key();

    /**
     * 锁等待时间，默认0秒
     */
    long waitTime() default 0;

    /**
     * 锁自动释放时间，默认10秒
     */
    long leaseTime() default -1;

    /**
     * 时间单位 默认单位 秒
     */
    TimeUnit unit() default TimeUnit.SECONDS;

    /**
     * 是否公平锁
     */
    boolean isFair() default false;

    /**
     * 缓存类型（ redis、zk ）
     */
    CacheTypeEnum cacheType() default CacheTypeEnum.REDIS;

    /**
     * 锁类型（ 默认、读、写 ）
     */
    LockTypeEnum lockType() default LockTypeEnum.REENTRANT_LOCK;
}
