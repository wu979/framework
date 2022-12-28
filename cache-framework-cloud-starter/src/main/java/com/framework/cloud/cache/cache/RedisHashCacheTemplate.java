package com.framework.cloud.cache.cache;

import cn.hutool.core.collection.CollectionUtil;
import com.framework.cloud.cache.properties.CacheAutoProperties;
import com.framework.cloud.cache.utils.CacheUtil;
import com.framework.cloud.common.utils.FastJsonUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 缓存代理
 *
 * @author wusiwei
 */
@RequiredArgsConstructor
public class RedisHashCacheTemplate implements RedisHashCache {

    private final RedisTemplate<String, Object> redisTemplate;
    private final CacheAutoProperties cacheAutoProperties;

    @Override
    public CacheAutoProperties properties() {
        return cacheAutoProperties;
    }

    @Override
    public <T> T get(String key, String hashKey, Class<T> clz) {
        Object value = redisTemplate.opsForHash().get(key, hashKey);
        if (CacheUtil.isNullOrBlank(value)) {
            return null;
        }
        if (!CacheUtil.isTarget(clz)) {
            return (T) value;
        }
        return FastJsonUtil.toJavaObject(value, FastJsonUtil.makeJavaType(clz));
    }

    @Override
    public <T> List<T> getAll(String key, Class<T> clz) {
        List<Object> values = redisTemplate.opsForHash().values(key);
        if (CollectionUtil.isEmpty(values)) {
            return Lists.newArrayList();
        }
        return FastJsonUtil.toJavaList(values, clz);
    }

    @Override
    public <K, T> Map<K, T> getAll(String key, Class<K> keyClz, Class<T> valueClz) {
        Map<Object, Object> values = redisTemplate.opsForHash().entries(key);
        if (CollectionUtil.isEmpty(values)) {
            return Maps.newHashMap();
        }
        Map<K, T> entries = new HashMap<>(values.size());
        for (Map.Entry<Object, Object> value : values.entrySet()) {
            entries.put((K)value.getKey(), FastJsonUtil.toJavaObject(value.getValue(), valueClz));
        }
        return entries;
    }

    @Override
    public <T> boolean put(String key, String hashKey, T value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
        return true;
    }

    @Override
    public <T> boolean putAll(String key, String hashKey, Map<Object, T> values) {
        redisTemplate.opsForHash().putAll(key, values);
        return true;
    }

    @Override
    public boolean hasKey(String key, String hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }
}
