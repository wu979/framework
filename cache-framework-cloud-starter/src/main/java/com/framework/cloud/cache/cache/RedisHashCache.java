package com.framework.cloud.cache.cache;

import com.framework.cloud.cache.properties.CacheAutoProperties;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * redis hash
 *
 * @author wusiwei
 */
public interface RedisHashCache {

    CacheAutoProperties properties();

    /**
     * 获取缓存
     */
    <T> T get(@NotBlank String key, @NotBlank String hashKey, Class<T> clz);

    /**
     * 获取缓存
     */
    <T> List<T> getAll(@NotBlank String key, Class<T> clz);

    /**
     * 获取缓存
     */
    <K, T> Map<K, T> getAll(@NotBlank String key, Class<K> keyClz, Class<T> valueClz);

    /**
     * 加入缓存
     */
    <T> boolean put(@NotBlank String key, @NotBlank String hashKey, T value);

    /**
     * 加入缓存
     */
    <T> boolean putAll(@NotBlank String key, @NotBlank String hashKey, Map<Object, T> values);

    /**
     * 获取缓存
     */
    boolean hasKey(@NotBlank String key, @NotBlank String hashKey);
}
