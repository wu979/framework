package com.framework.cloud.cache.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author wusiwei
 */
public class ZkDistributedLockImpl implements ZkDistributedLock {

    @Override
    public AsuraLock lock(String key, long leaseTime, TimeUnit unit, boolean isFair) {
        return null;
    }

    @Override
    public AsuraLock tryLock(String key, long waitTime, long leaseTime, TimeUnit unit, boolean isFair) {
        return null;
    }

    @Override
    public AsuraLock readLock(String key, long leaseTime, TimeUnit unit) {
        return null;
    }

    @Override
    public AsuraLock writeLock(String key, long leaseTime, TimeUnit unit) {
        return null;
    }

    @Override
    public void unlock(Object lock) {

    }
}
