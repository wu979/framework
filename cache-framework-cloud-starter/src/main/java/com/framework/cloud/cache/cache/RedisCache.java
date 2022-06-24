package com.framework.cloud.cache.cache;

import javax.validation.constraints.NotBlank;
import java.util.concurrent.TimeUnit;

/**
 * redis
 *
 * @author wusiwei
 */
public interface RedisCache extends Cache {

    /**
     * 获取缓存
     *
     * @param cacheLoader 缓存加载
     */
    <T> T get(@NotBlank String key, Class<T> clazz, CacheLoader<T> cacheLoader);

    /**
     * 获取缓存
     *
     * @param cacheLoader 缓存加载
     */
    <T> T get(@NotBlank String key, Class<T> clazz, CacheLoader<T> cacheLoader, long timeout, TimeUnit timeUnit);

    /**
     * 以布隆、分布式锁 获取缓存
     *
     * @param cacheLoader 缓存加载
     * @see "https://github.com/mabaiwan"
     */
    <T> T safeGet(@NotBlank String key, Class<T> clazz, CacheLoader<T> cacheLoader);

    /**
     * 以布隆、分布式锁 获取缓存
     *
     * @param cacheLoader 缓存加载
     * @see "https://github.com/mabaiwan"
     */
    <T> T safeGet(@NotBlank String key, Class<T> clazz, CacheLoader<T> cacheLoader, long timeout, TimeUnit timeUnit);

    /**
     * 加入缓存 指定缓存时间
     */
    boolean put(@NotBlank String key, Object value, long timeout, TimeUnit unit);

    /**
     * 加入缓存 默认时间秒
     */
    default boolean put(@NotBlank String key, Object value, long timeout) {
        return put(key, value, timeout, TimeUnit.SECONDS);
    }
}
