package com.framework.cloud.cache.annotation;

import com.framework.cloud.cache.enums.LockMedium;
import com.framework.cloud.cache.enums.ReadWriteLock;

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

    /**
     * 时间单位
     */
    TimeUnit unit() default TimeUnit.SECONDS;

    /**
     * 是否公平锁
     */
    boolean isFair() default false;

    /**
     * 锁 介质 （redis、zk）
     */
    LockMedium cacheType() default LockMedium.REDIS;

    /**
     * 默认、读写锁
     */
    ReadWriteLock lockType() default ReadWriteLock.LOCK;
}
