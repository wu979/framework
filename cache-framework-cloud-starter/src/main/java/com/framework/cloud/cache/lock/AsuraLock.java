package com.framework.cloud.cache.lock;

import lombok.AllArgsConstructor;

/**
 * @author wusiwei
 */
@AllArgsConstructor
public class AsuraLock implements AutoCloseable {

    private final Object lock;

    private final DistributedLock distributedLock;

    @Override
    public void close() {
        this.distributedLock.unlock(lock);
    }

    public Object getLock() {
        return lock;
    }
}
