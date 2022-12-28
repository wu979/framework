package com.framework.cloud.cache.cache;

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

    /**
     * 获取缓存
     *
     * @param cacheLoader 缓存加载
     */
    <T> T get(@NotBlank String key, Class<T> clz, CacheLoader<T> cacheLoader);

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
     * 获取缓存
     */
    <T> List<T> getSet(@NotBlank String key, Class<T> clz);

    /**
     * 以布隆、分布式锁 获取缓存
     *
     * @param cacheLoader 缓存加载
     * @see "https://github.com/agentart"
     */
    <T> T safeGet(@NotBlank String key, Class<T> clz, CacheLoader<T> cacheLoader);

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
    <T> boolean putAll(@NotBlank String key, List<T> value);

    /**
     * 缓存集合 Key -> List
     */
    <T> boolean putAll(@NotBlank String key, List<T> value, long timeout, TimeUnit unit);

    /**
     * 批量缓存单个
     */
    <T> boolean putMap(@NotBlank String prefix, Map<String, T> map, long timeout, TimeUnit unit);

    /**
     * 缓存Set List
     */
    <T> boolean add(@NotBlank String key, T... values);

    /**
     * 缓存Set List
     */
    <T> boolean add(@NotBlank String key, long timeout, TimeUnit unit, T... values);

    /**
     * 设置缓存不过期
     */
    boolean persist(@NotBlank String key);

    /**
     * 删除缓存 返回成功数量
     */
    long delete(@NotNull Collection<String> keys);

    /**
     * 加入缓存 默认时间秒
     */
    default boolean put(@NotBlank String key, Object value, long timeout) {
        return put(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 删除缓存 返回成功数量
     */
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