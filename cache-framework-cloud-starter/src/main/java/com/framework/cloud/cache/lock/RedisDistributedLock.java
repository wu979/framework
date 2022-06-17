package com.framework.cloud.cache.lock;

import java.util.concurrent.TimeUnit;

/**
 * redisson interface
 *
 * @author wusiwei
 */
public interface RedisDistributedLock extends DistributedLock {

    default void unlock(AsuraLock asuraLock) {
        if (asuraLock != null) {
            asuraLock.close();
        }
    }

    default AsuraLock lock(String key, long leaseTime, TimeUnit unit) {
        return this.lock(key, leaseTime, unit, false);
    }

    default AsuraLock lock(String key, boolean isFair) {
        return this.lock(key, -1, null, isFair);
    }

    default AsuraLock lock(String key) {
        return this.lock(key, false);
    }

    default AsuraLock tryLock(String key, long waitTime, long leaseTime, TimeUnit unit) {
        return this.tryLock(key, waitTime, leaseTime, unit, false);
    }

    default AsuraLock tryLock(String key, long waitTime, TimeUnit unit, boolean isFair) {
        return this.tryLock(key, waitTime, -1, unit, isFair);
    }

    default AsuraLock tryLock(String key, long waitTime, TimeUnit unit) {
        return this.tryLock(key, waitTime, -1, unit, false);
    }

    default AsuraLock readLock(String key) {
        return this.readLock(key, -1, null);
    }

    default AsuraLock writeLock(String key) {
        return this.writeLock(key, -1, null);
    }
}
