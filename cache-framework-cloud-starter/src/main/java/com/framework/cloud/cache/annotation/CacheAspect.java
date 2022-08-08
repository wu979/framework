package com.framework.cloud.cache.annotation;

import cn.hutool.core.util.ObjectUtil;
import com.framework.cloud.cache.cache.LocalCache;
import com.framework.cloud.cache.cache.RedisCache;
import com.framework.cloud.cache.enums.CacheMedium;
import com.framework.cloud.cache.enums.CacheType;
import com.framework.cloud.cache.properties.CacheAutoProperties;
import com.framework.cloud.cache.utils.SpElUtil;
import com.framework.cloud.common.enums.GlobalNumber;
import com.framework.cloud.common.exception.CacheException;
import com.framework.cloud.common.exception.LockException;
import jodd.util.StringPool;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;

import java.lang.reflect.Method;

/**
 * aop method for intercepting CloudCache annotation
 *
 * @author wusiwei
 */
@Slf4j
@Aspect
@RequiredArgsConstructor
public class CacheAspect implements Ordered {

    private final LocalCache localCache;
    private final RedisCache redisCache;
    private final CacheAutoProperties cacheAutoProperties;

    @Pointcut("@annotation(com.framework.cloud.cache.annotation.Cache)")
    public void cache() {}

    @Around(value = "@annotation(com.framework.cloud.cache.annotation.Cache)")
    public Object doAround(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Cache cache = method.getAnnotation(Cache.class);
        if (cache == null) {
            cache = point.getTarget().getClass().getAnnotation(Cache.class);
        }
        if (cache == null) {
            throw new CacheException("Cache is null");
        }
        String cacheKey = cache.key();
        if (StringUtils.isEmpty(cacheKey)) {
            throw new LockException("Cache key is null");
        }
        if (cacheKey.contains(StringPool.HASH)) {
            cacheKey = SpElUtil.analysisValBySpEl(cacheKey, signature, point.getArgs());
        }
        return result(cacheKey, cache, signature.getMethod(), point);
    }

    private Object result(String cacheKey, Cache cache, Method method, ProceedingJoinPoint point) {
        CacheType type = cache.type();
        Object result = null;
        if (CacheType.READ.equals(type)) {
            result = readObject(cacheKey, cache, method, point).getResult();
        }
        if (CacheType.DELETE.equals(type)) {
            result = deleteObject(cacheKey, cache, point);
        }
        if (CacheType.PUT.equals(type)) {
            result = putObject(cacheKey, cache, point);
        }
        if (CacheType.FULL.equals(type)) {
            result = fullObject(cacheKey, cache, method, point);
        }
        return result;
    }

    @SneakyThrows
    private ReadObject readObject(String cacheKey, Cache cache, Method method, ProceedingJoinPoint point) {
        CacheMedium medium = cache.medium();
        Class<?> returnType = method.getReturnType();
        Object result = null;
        boolean isCache = Boolean.TRUE;
        // 指定本地缓存 为空 执行代理
        if (CacheMedium.LOCAL.equals(medium)) {
            result = localCache.get(cacheKey, returnType);
            if (ObjectUtil.isNull(result)) {
                result = point.proceed();
                isCache = false;
            }
        }
        if (CacheMedium.DISTRIBUTED.equals(medium)) {
            result = redisCache.get(cacheKey, returnType);
            if (ObjectUtil.isNull(result)) {
                result = point.proceed();
                isCache = false;
            }
        }
        if (CacheMedium.FULL.equals(medium)) {
            result = localCache.get(cacheKey, returnType);
            if (ObjectUtil.isNull(result)) {
                result = redisCache.get(cacheKey, returnType);
                if (ObjectUtil.isNull(result)) {
                    result = point.proceed();
                    isCache = false;
                }
            }
        }
        return new ReadObject(result, isCache);
    }

    @SneakyThrows
    private Object deleteObject(String cacheKey, Cache cache, ProceedingJoinPoint point) {
        Object proceed = point.proceed();
        if (ObjectUtil.isNotNull(proceed)) {
            if (proceed instanceof Boolean) {
                Boolean delete = (Boolean) proceed;
                if (delete) {
                    CacheMedium medium = cache.medium();
                    if (CacheMedium.LOCAL.equals(medium)) {
                        localCache.delete(cacheKey);
                    }
                    if (CacheMedium.DISTRIBUTED.equals(medium)) {
                        redisCache.delete(cacheKey);
                    }
                    if (CacheMedium.FULL.equals(medium)) {
                        localCache.delete(cacheKey);
                        redisCache.delete(cacheKey);
                    }
                }
            }
        }
        return proceed;
    }

    @SneakyThrows
    private Object putObject(String cacheKey, Cache cache, ProceedingJoinPoint point) {
        Object proceed = point.proceed();
        if (ObjectUtil.isNotNull(proceed)) {
            localCache.put(cacheKey, proceed);
            redisCache.put(cacheKey, proceed, timeout(cache.timeout()), cache.unit());
        }
        return proceed;
    }

    @SneakyThrows
    private Object fullObject(String cacheKey, Cache cache, Method method, ProceedingJoinPoint point) {
        ReadObject readObject = readObject(cacheKey, cache, method, point);
        Object proceed = readObject.getResult();
        if (ObjectUtil.isNull(proceed)) {
            return proceed;
        }
        Boolean isCache = readObject.getIsCache();
        if (!isCache) {
            CacheMedium medium = cache.medium();
            if (CacheMedium.LOCAL.equals(medium)) {
                localCache.put(cacheKey, proceed);
            }
            if (CacheMedium.DISTRIBUTED.equals(medium)) {
                redisCache.put(cacheKey, proceed, timeout(cache.timeout()), cache.unit());
            }
            if (CacheMedium.FULL.equals(medium)) {
                localCache.put(cacheKey, proceed);
                redisCache.put(cacheKey, proceed, timeout(cache.timeout()), cache.unit());
            }
        }
        return proceed;
    }

    private long timeout(long timeout) {
        if (GlobalNumber.ZERO.getLongValue().equals(timeout)) {
            return cacheAutoProperties.getCacheTimeout();
        }
        return timeout;
    }

    @Override
    public int getOrder() {
        return 100;
    }
}
