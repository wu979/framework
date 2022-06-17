package com.framework.cloud.cache.lock;

import java.util.concurrent.TimeUnit;

/**
 * distributed Lock interface
 *
 * @author wusiwei
 */
@SuppressWarnings("dep-ann")
public interface DistributedLock {

    /**
     * 获取锁 等待锁释放 直到获取
     *
     * @param key       锁key
     * @param leaseTime 加锁的时间，超过时间后自动解锁；-1，则保持锁定直到显式解锁
     * @param unit      锁时间单位
     * @param isFair    是否公平锁
     */
    AsuraLock lock(String key, long leaseTime, TimeUnit unit, boolean isFair);

    /**
     * 获取锁 等待waitTime后释放锁
     *
     * @param key       锁key
     * @param waitTime  最大等待时间
     * @param leaseTime 加锁的时间，超过时间后自动解锁；-1，则保持锁定直到显式解锁
     * @param unit      锁时间单位
     * @param isFair    是否公平锁
     */
    AsuraLock tryLock(String key, long waitTime, long leaseTime, TimeUnit unit, boolean isFair);

    /**
     * 获取读锁
     *
     * @param key       锁key
     * @param leaseTime 加锁的时间，超过时间后自动解锁；-1，则保持锁定直到显式解锁
     * @param unit      锁时间单位
     */
    AsuraLock readLock(String key, long leaseTime, TimeUnit unit);

    /**
     * 获取写锁
     *
     * @param key       锁key
     * @param leaseTime 加锁的时间，超过时间后自动解锁；-1，则保持锁定直到显式解锁
     * @param unit      锁时间单位
     */
    AsuraLock writeLock(String key, long leaseTime, TimeUnit unit);

    /**
     * 关闭锁
     */
    void unlock(Object lock);

}
