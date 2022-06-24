package com.framework.cloud.cache.cache;

import com.framework.cloud.cache.lock.AsuraLock;
import com.framework.cloud.cache.lock.RedisDistributedLock;
import com.framework.cloud.cache.properties.RedisAutoProperties;
import com.framework.cloud.cache.utils.CacheUtil;
import com.framework.cloud.common.enums.GlobalNumber;
import com.framework.cloud.common.utils.FastJsonUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RBloomFilter;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 缓存模板
 *
 * @author wusiwei
 */
@RequiredArgsConstructor
public class RedisCacheTemplate implements RedisCache {

    private static final String LOCK_KEY = "redisCache_lock_get_key";
    private final StringRedisTemplate stringRedisTemplate;
    private final RedisDistributedLock redisDistributedLock;
    private final RedisAutoProperties redisAutoProperties;
    private final RBloomFilter<String> cachePenetrationBloomFilter;

    @Override
    public <T> T get(@NotBlank String key, Class<T> clz) {
        String value = stringRedisTemplate.opsForValue().get(key);
        if (CacheUtil.isNullOrBlank(value)) {
            return null;
        }
        if (!CacheUtil.isCacheTarget(value)) {
            return (T) value;
        }
        return FastJsonUtil.toJavaObject(value, FastJsonUtil.makeJavaType(clz));
    }

    @Override
    public boolean put(@NotBlank String key, Object value) {
        return put(key, value, redisAutoProperties.getCacheTimeout(), redisAutoProperties.getCacheTimeoutUnit());
    }

    @Override
    public boolean delete(@NotBlank String key) {
        Boolean delete = stringRedisTemplate.delete(key);
        return null != delete && delete;
    }

    @Override
    public long delete(@NotNull Collection<String> keys) {
        Long delete = stringRedisTemplate.delete(keys);
        return null != delete ? delete : 0L;
    }

    @Override
    public long delete(@NotNull String... key) {
        List<String> keys = Stream.of(key).filter(StringUtils::isNotBlank).collect(Collectors.toList());
        Long delete = stringRedisTemplate.delete(keys);
        return null != delete ? delete : 0L;
    }

    @Override
    public boolean hasKey(@NotBlank String key) {
        Boolean has =  stringRedisTemplate.hasKey(key);
        return null != has && has;
    }

    @Override
    public <T> T get(@NotBlank String key, Class<T> clazz, CacheLoader<T> cacheLoader) {
        return get(key, clazz, cacheLoader, redisAutoProperties.getCacheTimeout(), redisAutoProperties.getCacheTimeoutUnit());
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
        return safeGet(key, clazz, cacheLoader, redisAutoProperties.getCacheTimeout(), redisAutoProperties.getCacheTimeoutUnit());
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
        String jsonValue = CacheUtil.isCacheTarget(value) ? FastJsonUtil.toJSONString(value) : (String) value;
        if (timeout < GlobalNumber.ZERO.getIntValue()) {
            stringRedisTemplate.opsForValue().set(key, jsonValue);
            stringRedisTemplate.persist(key);
            return true;
        }
        stringRedisTemplate.opsForValue().set(key, jsonValue, timeout, unit);
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
