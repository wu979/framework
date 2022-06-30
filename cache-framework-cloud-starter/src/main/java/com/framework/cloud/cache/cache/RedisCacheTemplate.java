package com.framework.cloud.cache.cache;

import com.framework.cloud.cache.lock.AsuraLock;
import com.framework.cloud.cache.lock.RedisDistributedLock;
import com.framework.cloud.cache.properties.CacheAutoProperties;
import com.framework.cloud.cache.utils.CacheUtil;
import com.framework.cloud.common.enums.GlobalNumber;
import com.framework.cloud.common.utils.FastJsonUtil;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.springframework.data.redis.core.RedisTemplate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * 缓存模板
 *
 * @author wusiwei
 */
@RequiredArgsConstructor
public class RedisCacheTemplate implements RedisCache {

    private static final String LOCK_KEY = "redisCache_lock_get_key";
    private final RedisTemplate<String, Object> redisTemplate;
    private final RedisDistributedLock redisDistributedLock;
    private final CacheAutoProperties cacheAutoProperties;
    private final RBloomFilter<String> cachePenetrationBloomFilter;

    @Override
    public <T> T get(@NotBlank String key, Class<T> clz) {
        Object value = redisTemplate.opsForValue().get(key);
        if (CacheUtil.isNullOrBlank(value)) {
            return null;
        }
        if (!CacheUtil.isTarget(clz)) {
            return (T) value;
        }
        return FastJsonUtil.toJavaObject(value, FastJsonUtil.makeJavaType(clz));
    }

    @Override
    public boolean put(@NotBlank String key, Object value) {
        return put(key, value, cacheAutoProperties.getCacheTimeout(), cacheAutoProperties.getCacheTimeoutUnit());
    }

    @Override
    public boolean delete(@NotBlank String key) {
        Boolean delete = redisTemplate.delete(key);
        return null != delete && delete;
    }

    @Override
    public long delete(@NotNull Collection<String> keys) {
        Long delete = redisTemplate.delete(keys);
        return null != delete ? delete : 0L;
    }

    @Override
    public boolean hasKey(@NotBlank String key) {
        Boolean has = redisTemplate.hasKey(key);
        return null != has && has;
    }

    @Override
    public <T> T get(@NotBlank String key, Class<T> clazz, CacheLoader<T> cacheLoader) {
        return get(key, clazz, cacheLoader, cacheAutoProperties.getCacheTimeout(), cacheAutoProperties.getCacheTimeoutUnit());
    }

    @Override
    public <T> T get(@NotBlank String key, Class<T> clazz, CacheLoader<T> cacheLoader, long timeout, TimeUnit timeUnit) {
        T value = get(key, clazz);
        if (!CacheUtil.isNullOrBlank(value)) {
            return value;
        }
        return loadAndSet(key, cacheLoader, timeout, timeUnit);
    }

    @Override
    public <T> T safeGet(@NotBlank String key, Class<T> clazz, CacheLoader<T> cacheLoader) {
        return safeGet(key, clazz, cacheLoader, cacheAutoProperties.getCacheTimeout(), cacheAutoProperties.getCacheTimeoutUnit());
    }

    @Override
    public <T> T safeGet(@NotBlank String key, Class<T> clazz, CacheLoader<T> cacheLoader, long timeout, TimeUnit timeUnit) {
        T result = get(key, clazz);
        if (!CacheUtil.isNullOrBlank(result)) {
            return result;
        }
        // 判断布隆过滤器是否存在，存在返回空
        if (cachePenetrationBloomFilter.contains(key)) {
            return null;
        }
        try (AsuraLock ignored = redisDistributedLock.lock(LOCK_KEY + key)) {
            // 双重判定锁，减轻数据库访问压力
            if (CacheUtil.isNullOrBlank(result = get(key, clazz))) {
                // 通过 load 接口加载为空，触发缓存穿透条件，把 key 放入布隆过滤器
                if (CacheUtil.isNullOrBlank(result = loadAndSet(key, cacheLoader, timeout, timeUnit))) {
                    cachePenetrationBloomFilter.add(key);
                }
            }
        }
        return result;
    }

    @Override
    public boolean put(@NotBlank String key, Object value, long timeout, TimeUnit unit) {
        if (CacheUtil.isTarget(value)) {
            if (timeout < GlobalNumber.ZERO.getIntValue()) {
                redisTemplate.opsForValue().set(key, FastJsonUtil.toJSONString(value));
                redisTemplate.persist(key);
            } else {
                redisTemplate.opsForValue().set(key, FastJsonUtil.toJSONString(value), timeout, unit);
            }
        } else {
            if (timeout < GlobalNumber.ZERO.getIntValue()) {
                redisTemplate.opsForValue().set(key, value);
                redisTemplate.persist(key);
            } else {
                redisTemplate.opsForValue().set(key, value, timeout, unit);
            }
        }
        return true;
    }

    private <T> T loadAndSet(String key, CacheLoader<T> cacheLoader, long timeout, TimeUnit timeUnit) {
        T value = cacheLoader.load();
        if (CacheUtil.isNullOrBlank(value)) {
            return value;
        }
        put(key, value, timeout, timeUnit);
        return value;
    }
}
