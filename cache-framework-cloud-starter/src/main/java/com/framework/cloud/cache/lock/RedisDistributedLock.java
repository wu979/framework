package com.framework.cloud.cache.lock;

import java.util.concurrent.TimeUnit;

/**
 * redisson 分布式锁接口
 *
 * @author wusiwei
 */
public interface RedisDistributedLock extends DistributedLock {

    default void unlock(AsuraLock asuraLock) throws Exception {
        if (asuraLock != null) {
            asuraLock.close();
        }
    }

    default AsuraLock lock(String key, long leaseTime, TimeUnit unit) throws Exception {
        return this.lock(key, leaseTime, unit, false);
    }

    default AsuraLock lock(String key, boolean isFair) throws Exception {
        return this.lock(key, -1, null);
    }

    default AsuraLock lock(String key) throws Exception {
        return this.lock(key, false);
    }

    default AsuraLock tryLock(String key, long waitTime, long leaseTime, TimeUnit unit) throws Exception {
        return this.tryLock(key, waitTime, leaseTime, unit, false);
    }

    default AsuraLock tryLock(String key, long waitTime, TimeUnit unit, boolean isFair) throws Exception {
        return this.tryLock(key, waitTime, -1, unit, isFair);
    }

    default AsuraLock tryLock(String key, long waitTime, TimeUnit unit) throws Exception {
        return this.tryLock(key, waitTime, -1, unit, false);
    }

    default AsuraLock readLock(String key) throws Exception {
        return this.readLock(key, -1, null);
    }

    default AsuraLock writeLock(String key) throws Exception {
        return this.writeLock(key, -1, null);
    }
}
