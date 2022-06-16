package com.framework.cloud.cache.lock;

import lombok.AllArgsConstructor;

/**
 * 阿修罗锁
 *
 * @author wusiwei
 */
@AllArgsConstructor
public class AsuraLock implements AutoCloseable {

    private final Object lock;

    private final DistributedLock distributedLock;

    @Override
    public void close() throws Exception {
        this.distributedLock.unlock(lock);
    }

    public Object getLock() {
        return lock;
    }
}
