package com.framework.cloud.cache.cache;

import cn.hutool.core.collection.CollectionUtil;
import com.framework.cloud.cache.properties.CacheAutoProperties;
import com.framework.cloud.common.utils.FastJsonUtil;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 缓存代理
 *
 * @author wusiwei
 */
@RequiredArgsConstructor
public class RedisSetCacheTemplate implements RedisSetCache {

    private final RedisTemplate<String, Object> redisTemplate;
    private final CacheAutoProperties cacheAutoProperties;

    @Override
    public CacheAutoProperties properties() {
        return cacheAutoProperties;
    }

    @Override
    public boolean expire(String key, long timeout, TimeUnit unit) {
        Boolean expire = redisTemplate.expire(key, timeout, unit);
        return expire != null && expire;
    }

    @Override
    public <T> Set<T> get(String key, Class<T> clz) {
        Set<Object> values = redisTemplate.opsForSet().members(key);
        if (CollectionUtil.isEmpty(values)) {
            return Sets.newHashSet();
        }
        return FastJsonUtil.toJavaSet(values, clz);
    }

    @Override
    public <T> boolean add(String key, List<T> values, long timeout, TimeUnit unit) {
        for (T value : values) {
            if (value instanceof String) {
                redisTemplate.opsForSet().add(key, value);
            } else {
                redisTemplate.opsForSet().add(key, FastJsonUtil.toJSONString(value));
            }
        }
        redisTemplate.expire(key, timeout, unit);
        return true;
    }
}
