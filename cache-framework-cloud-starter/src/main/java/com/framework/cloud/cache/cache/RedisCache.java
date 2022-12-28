package com.framework.cloud.cache.cache;

import com.framework.cloud.cache.properties.CacheAutoProperties;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * redis
 *
 * @author wusiwei
 */
public interface RedisCache extends MultistageCache {

    CacheAutoProperties properties();

    /**
     * 获取缓存
     *
     * @param cacheLoader 缓存加载
     */
    <T> T get(@NotBlank String key, Class<T> clz, CacheLoader<T> cacheLoader, long timeout, TimeUnit timeUnit);

    /**
     * 获取缓存
     */
    <T> List<T> getAll(@NotBlank String key, Class<T> clz);

    /**
     * 以布隆、分布式锁 获取缓存
     *
     * @param cacheLoader 缓存加载
     * @see "https://github.com/agentart"
     */
    <T> T safeGet(@NotBlank String key, Class<T> clz, CacheLoader<T> cacheLoader, long timeout, TimeUnit timeUnit);

    /**
     * 加入缓存 指定缓存时间
     */
    <T> boolean put(@NotBlank String key, T value, long timeout, TimeUnit unit);

    /**
     * 缓存集合 Key -> List
     */
    <T> boolean putAll(@NotBlank String key, List<T> values, long timeout, TimeUnit unit);

    /**
     * 批量缓存单个
     */
    <T> boolean putMap(@NotBlank String prefix, Map<String, T> map, long timeout, TimeUnit unit);

    /**
     * 设置缓存不过期
     */
    boolean persist(@NotBlank String key);

    /**
     * 删除缓存 返回成功数量
     */
    long delete(@NotNull Collection<String> keys);

    default <T> T get(@NotBlank String key, Class<T> clz, CacheLoader<T> cacheLoader) {
        return get(key, clz, cacheLoader, properties().getCacheTimeout(), properties().getCacheTimeoutUnit());
    }

    default <T> T safeGet(@NotBlank String key, Class<T> clz, CacheLoader<T> cacheLoader) {
        return safeGet(key, clz, cacheLoader, properties().getCacheTimeout(), properties().getCacheTimeoutUnit());
    }

    default boolean put(@NotBlank String key, Object value, long timeout) {
        return put(key, value, timeout, TimeUnit.SECONDS);
    }

    default <T> boolean putAll(@NotBlank String key, List<T> values) {
        return putAll(key, values, properties().getCacheTimeout(), properties().getCacheTimeoutUnit());
    }

    default long delete(@NotNull String... key) {
        return delete(Stream.of(key).filter(StringUtils::isNotBlank).collect(Collectors.toList()));
    }

    default <T> boolean putMap(@NotBlank String prefix, Map<String, T> map) {
        for (Map.Entry<String, T> row : map.entrySet()) {
            put(prefix + row.getKey(), row.getValue());
        }
        return true;
    }
}