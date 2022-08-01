package com.framework.cloud.cache.cache;

import javax.validation.constraints.NotBlank;

/**
 * cache
 *
 * @author wusiwei
 */
public interface MultistageCache {

    /**
     * 获取缓存
     */
    <T> T get(@NotBlank String key, Class<T> clz);

    /**
     * 加入缓存 默认缓存时间
     */
    <T> boolean put(@NotBlank String key, T value);

    /**
     * 删除缓存
     */
    boolean delete(@NotBlank String key);

    /**
     * key是否存在
     */
    boolean hasKey(@NotBlank String key);
}
