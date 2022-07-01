package com.framework.cloud.cache.annotation;

import com.framework.cloud.cache.enums.LockMedium;
import com.framework.cloud.cache.lock.AsuraLock;
import com.framework.cloud.cache.lock.DistributedLock;
import com.framework.cloud.cache.lock.RedisDistributedLock;
import com.framework.cloud.cache.lock.ZkDistributedLock;
import com.framework.cloud.cache.utils.SpElUtil;
import com.framework.cloud.common.exception.LockException;
import jodd.util.StringPool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;

import java.lang.reflect.Method;

/**
 * aop method for intercepting lock annotation
 *
 * @author wusiwei
 */
@Slf4j
@Aspect
@RequiredArgsConstructor
public class LockAspect implements Ordered {

    private final RedisDistributedLock redisDistributedLock;
    private final ZkDistributedLock zkDistributedLock;

    @Around(value = "@annotation(com.framework.cloud.cache.annotation.Lock)")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Lock lock = method.getAnnotation(Lock.class);
        if (lock == null) {
            lock = point.getTarget().getClass().getAnnotation(Lock.class);
        }
        if (lock == null) {
            throw new LockException("Lock is null");
        }
        String lockKey = lock.key();
        if (StringUtils.isEmpty(lockKey)) {
            throw new LockException("Lock key is null");
        }
        DistributedLock distributedLock;
        if (LockMedium.ZK.equals(lock.cacheType())) {
            distributedLock = zkDistributedLock;
        } else {
            distributedLock = redisDistributedLock;
        }
        if (null == distributedLock) {
            throw new LockException("not init distributedLock");
        }
        if (lockKey.contains(StringPool.HASH)) {
            lockKey = SpElUtil.analysisValBySpEl(lockKey, signature, point.getArgs());
        }
        AsuraLock asuraLock = null;
        try {
            switch (lock.lockType()) {
                case LOCK:
                    if (lock.waitTime() > 0) {
                        asuraLock = distributedLock.tryLock(lockKey, lock.waitTime(), lock.leaseTime(), lock.unit(), lock.isFair());
                    } else {
                        asuraLock = distributedLock.lock(lockKey, lock.leaseTime(), lock.unit(), lock.isFair());
                    }
                    break;
                case READ:
                    asuraLock = distributedLock.readLock(lockKey, lock.leaseTime(), lock.unit());
                    break;
                case WRITE:
                    asuraLock = distributedLock.writeLock(lockKey, lock.leaseTime(), lock.unit());
                    break;
                default:
                    break;
            }
            if (asuraLock != null) {
                return point.proceed();
            } else {
                throw new LockException("Lock wait timeout");
            }
        } finally {
            distributedLock.unlock(asuraLock);
        }
    }

    @Override
    public int getOrder() {
        return 90;
    }
}
