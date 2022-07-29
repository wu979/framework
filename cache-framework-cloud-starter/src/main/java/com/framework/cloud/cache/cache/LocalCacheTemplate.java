package com.framework.cloud.cache.cache;

import com.framework.cloud.cache.utils.CacheUtil;
import com.framework.cloud.common.exception.CacheException;
import com.framework.cloud.common.utils.FastJsonUtil;
import com.github.benmanes.caffeine.cache.Cache;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wusiwei
 */
@RequiredArgsConstructor
public class LocalCacheTemplate implements LocalCache {

    private final Cache<Object, Object> cache;

    @Override
    public <T> T get(@NotBlank String key, Class<T> clz) {
        Object value = cache.getIfPresent(key);
        if (CacheUtil.isNullOrBlank(value)) {
            return null;
        }
        if (!CacheUtil.isTarget(clz)) {
            return (T) value;
        }
        return FastJsonUtil.toJavaObject(value, FastJsonUtil.makeJavaType(clz));
    }

    @Override
    public <T> T get(@NotBlank String key, Class<T> clz, CacheLoader<T> cacheLoader) {
        T value = get(key, clz);
        if (!CacheUtil.isNullOrBlank(value)) {
            return value;
        }
        return loadAndSet(key, cacheLoader);
    }

    @Override
    public <T> List<T> getAll(String key, Class<T> clz) {
        return null;
    }

    @Override
    public boolean put(@NotBlank String key, Object value) {
        if (CacheUtil.isTarget(value)) {
            cache.put(key, FastJsonUtil.toJSONString(value));
        } else {
            cache.put(key, value);
        }
        return true;
    }

    @Override
    public boolean putAll(String key, List<Object> list) {
        return false;
    }

    @Override
    public boolean putAll(Map<String, Object> map) {
        cache.putAll(map);
        return true;
    }

    @Override
    public boolean putAll(String prefix, Map<String, Object> map) {
        Map<String, Object> newMap = new HashMap<>();
        for (Map.Entry<String, Object> row : map.entrySet()) {
            newMap.put(prefix + row.getKey(), row.getValue());
        }
        return putAll(newMap);
    }

    @Override
    public boolean delete(@NotBlank String key) {
        cache.invalidate(key);
        return true;
    }

    @Override
    public boolean delete(@NotNull Collection<String> keys) {
        cache.invalidateAll(keys);
        return true;
    }

    @Override
    public boolean hasKey(@NotBlank String key) {
        throw new CacheException("local cache interface is not supported");
    }

    private <T> T loadAndSet(String key, CacheLoader<T> cacheLoader) {
        T value = cacheLoader.load();
        if (CacheUtil.isNullOrBlank(value)) {
            return value;
        }
        put(key, value);
        return value;
    }
}
