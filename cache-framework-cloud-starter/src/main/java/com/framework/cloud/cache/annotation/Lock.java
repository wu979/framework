package com.framework.cloud.cache.annotation;

import com.framework.cloud.cache.enums.CacheTypeEnum;
import com.framework.cloud.cache.enums.LockTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * distributed lock are based on redis or ZK
 *
 * @author wusiwei
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Lock {

    String key();

    /**
     * >0 turn on tryLock
     */
    long waitTime() default 0;

    /**
     * auto release time
     */
    long leaseTime() default -1;

    TimeUnit unit() default TimeUnit.SECONDS;

    boolean isFair() default false;

    CacheTypeEnum cacheType() default CacheTypeEnum.REDIS;

    LockTypeEnum lockType() default LockTypeEnum.REENTRANT_LOCK;
}
