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
    <T> List<T> getAll(@NotBlank String key, Class<T> clz, long timeout, TimeUnit timeUnit);

    /**
     * 以布隆、分布式锁 获取缓存
     *
     * @param cacheLoader 缓存加载
     * @see "https://github.com/mabaiwan"
     */
    <T> T safeGet(@NotBlank String key, Class<T> clz, CacheLoader<T> cacheLoader);

    /**
     * 以布隆、分布式锁 获取缓存
     *
     * @param cacheLoader 缓存加载
     * @see "https://github.com/mabaiwan"
     */
    <T> T safeGet(@NotBlank String key, Class<T> clz, CacheLoader<T> cacheLoader, long timeout, TimeUnit timeUnit);

    /**
     * 加入缓存 指定缓存时间
     */
    boolean put(@NotBlank String key, Object value, long timeout, TimeUnit unit);

    /**
     * 缓存集合 Key -> List
     */
    boolean putAll(@NotBlank String key, List<Object> value, long timeout, TimeUnit unit);

    /**
     * 批量缓存单个
     */
    boolean putAll(@NotBlank String prefix, Map<String, Object> map, long timeout, TimeUnit unit);

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
}
