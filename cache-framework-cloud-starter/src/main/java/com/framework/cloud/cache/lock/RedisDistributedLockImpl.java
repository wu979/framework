package com.framework.cloud.cache.lock;

import com.framework.cloud.common.exception.LockException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * @author wusiwei
 */
@AllArgsConstructor
public class RedisDistributedLockImpl implements RedisDistributedLock {

    private static final String LOCK_KEY_PREFIX = "REDIS_KEY_PREFIX:";

    private final RedissonClient redissonClient;

    @Override
    public AsuraLock lock(String key, long leaseTime, TimeUnit unit, boolean isFair) {
        RLock lock = getLock(key, isFair);
        lock.lock(leaseTime, unit);
        return new AsuraLock(lock, this);
    }

    @SneakyThrows
    @Override
    public AsuraLock tryLock(String key, long waitTime, long leaseTime, TimeUnit unit, boolean isFair) {
        RLock lock = getLock(key, isFair);
        if (!lock.tryLock(waitTime, leaseTime, unit)) {
            return null;
        }
        return new AsuraLock(lock, this);
    }

    @Override
    public AsuraLock readLock(String key, long leaseTime, TimeUnit unit) {
        RLock lock = getReadWriteLock(key, Boolean.TRUE);
        lock.lock(leaseTime, unit);
        return new AsuraLock(lock, this);
    }

    @Override
    public AsuraLock writeLock(String key, long leaseTime, TimeUnit unit) {
        RLock lock = getReadWriteLock(key, Boolean.FALSE);
        lock.lock(leaseTime, unit);
        return new AsuraLock(lock, this);
    }

    @Override
    public void unlock(Object lock) {
        if (lock != null) {
            RLock rLock = null;
            if (lock instanceof RLock) {
                rLock = (RLock) lock;
            }
            if (lock instanceof AsuraLock) {
                rLock = (RLock) ((AsuraLock) lock).getLock();
            }
            if (rLock == null) {
                throw new LockException("Requires RLock Type");
            }
            if (rLock.isLocked()) {
                rLock.unlock();
            }
        }
    }

    private RLock getLock(String key, boolean isFair) {
        RLock lock;
        if (isFair) {
            lock = redissonClient.getFairLock(LOCK_KEY_PREFIX + key);
        } else {
            lock = redissonClient.getLock(LOCK_KEY_PREFIX + key);
        }
        return lock;
    }

    private RLock getReadWriteLock(String key, boolean isRead) {
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock(key);
        return isRead ? readWriteLock.readLock() : readWriteLock.writeLock();
    }

}
