package com.framework.cloud.cache.cache;

import com.framework.cloud.cache.properties.CacheAutoProperties;
import com.google.common.collect.Lists;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis set
 *
 * @author wusiwei
 */
public interface RedisSetCache {

    CacheAutoProperties properties();

    /**
     * 设置过期时间
     */
    boolean expire(@NotBlank String key, long timeout, TimeUnit unit);

    /**
     * 获取缓存
     */
    <T> Set<T> get(@NotBlank String key, Class<T> clz);

    /**
     * 缓存Set List
     */
    <T> boolean add(@NotBlank String key, List<T> values, long timeout, TimeUnit unit);

    default <T> boolean add(@NotBlank String key, T value) {
        return add(key, Lists.newArrayList(value), properties().getCacheTimeout(), properties().getCacheTimeoutUnit());
    }

    default <T> boolean add(@NotBlank String key, List<T> values) {
        return add(key, values, properties().getCacheTimeout(), properties().getCacheTimeoutUnit());
    }

    default <T> boolean add(@NotBlank String key, T value, long timeout, TimeUnit unit) {
        return add(key, Lists.newArrayList(value), timeout, unit);
    }
}
