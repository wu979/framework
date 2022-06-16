package com.framework.cloud.cache.annotation;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.framework.cloud.cache.enums.CacheTypeEnum;
import com.framework.cloud.cache.lock.AsuraLock;
import com.framework.cloud.cache.lock.DistributedLock;
import com.framework.cloud.cache.lock.RedisDistributedLock;
import com.framework.cloud.common.exception.LockException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * 分布式锁切面
 *
 * @author wusiwei
 */
@Slf4j
@Aspect
@AllArgsConstructor
@ConditionalOnBean(DistributedLock.class)
public class LockAspect {

    private final RedisDistributedLock redisDistributedLock;

    /**
     * 表达式解析
     */
    private static final SpelExpressionParser parser = new SpelExpressionParser();

    /**
     * 用于获取方法参数定义名字
     */
    private static final DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();

    /**
     * 注解切面
     */
    @Around(value = "@annotation(lock)", argNames = "point, lock")
    public Object processTest(ProceedingJoinPoint point, Lock lock) throws Throwable {
        if (lock == null) {
            lock = point.getTarget().getClass().getDeclaredAnnotation(Lock.class);
        }
        if (lock == null) {
            throw new LockException("Lock is null");
        }
        String lockKey = lock.key();
        if (StringUtils.isEmpty(lockKey)) {
            throw new LockException("Lock key is null");
        }
        DistributedLock distributedLock = null;
        if (CacheTypeEnum.REDIS.equals(lock.cacheType())) {
            distributedLock = redisDistributedLock;
        }
        if (null == distributedLock) {
            throw new LockException("not init distributedLock");
        }
        if (lockKey.contains(StringPool.HASH)) {
            MethodSignature methodSignature = (MethodSignature) point.getSignature();
            // 获取方法参数值
            Object[] args = point.getArgs();
            lockKey = getValBySpEl(lockKey, methodSignature, args);
        }
        AsuraLock asuraLock = null;
        try {
            switch (lock.lockType()) {
                case REENTRANT_LOCK:
                    if (lock.waitTime() > 0) {
                        asuraLock = distributedLock.tryLock(lockKey, lock.waitTime(), lock.leaseTime(), lock.unit(), lock.isFair());
                    } else {
                        asuraLock = distributedLock.lock(lockKey, lock.leaseTime(), lock.unit(), lock.isFair());
                    }
                    break;
                case REENTRANT_READ:
                    asuraLock = distributedLock.readLock(lockKey, lock.leaseTime(), lock.unit());
                    break;
                case REENTRANT_WRITE:
                    asuraLock = distributedLock.writeLock(lockKey, lock.leaseTime(), lock.unit());
                    break;
                default:
                    break;
            }
            if (asuraLock != null) {
                return point.proceed();
            } else {
                throw new LockException("锁等待超时");
            }
        } finally {
            distributedLock.unlock(asuraLock);
        }
    }


    /**
     * 解析 spEL 表达式
     */
    private String getValBySpEl(String lockKeyEl, MethodSignature methodSignature, Object[] args) {
        // 获取方法形参名数组
        String[] paramNames = discoverer.getParameterNames(methodSignature.getMethod());
        if (paramNames != null && paramNames.length > 0) {
            Expression expression = parser.parseExpression(lockKeyEl);
            // spring 表达式上下文对象
            EvaluationContext context = new StandardEvaluationContext();
            // 给上下文赋值
            for (int i = 0; i < args.length; i++) {
                context.setVariable(paramNames[i], args[i]);
            }
            return expression.getValue(context).toString();
        }
        return null;
    }

}
