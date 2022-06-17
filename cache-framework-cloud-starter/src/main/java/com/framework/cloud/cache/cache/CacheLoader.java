package com.framework.cloud.cache.cache;

/**
 * 缓存加载器
 *
 * @author wusiwei
 * @see "https://github.com/mabaiwan"
 */
@FunctionalInterface
public interface CacheLoader<T> {

    T load();
}
