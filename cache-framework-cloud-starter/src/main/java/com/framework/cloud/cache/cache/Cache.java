package com.framework.cloud.cache.cache;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * cache
 *
 * @author wusiwei
 */
public interface Cache {

    /**
     * 获取缓存
     */
    <T> T get(@NotBlank String key, Class<T> clz);

    /**
     * 加入缓存 默认缓存时间
     */
    boolean put(@NotBlank String key, Object value);

    /**
     * 删除缓存
     */
    boolean delete(@NotBlank String key);

    /**
     * 删除缓存 返回成功数量
     */
    long delete(@NotNull Collection<String> keys);

    /**
     * 删除缓存 返回成功数量
     */
    long delete(@NotNull String... key);

    /**
     * key是否存在
     */
    boolean hasKey(@NotBlank String key);
}
