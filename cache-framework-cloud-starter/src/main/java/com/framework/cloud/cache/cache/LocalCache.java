package com.framework.cloud.cache.cache;

import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * caffeine
 *
 * @author wusiwei
 */
public interface LocalCache extends Cache {

    /**
     * 获取缓存
     *
     * @param cacheLoader 缓存加载
     */
    <T> T get(@NotBlank String key, Class<T> clazz, CacheLoader<T> cacheLoader);

    /**
     * 删除缓存
     */
    boolean delete(@NotNull Collection<String> keys);


    /**
     * 删除缓存 返回成功数量
     */
    default boolean delete(@NotNull String... key) {
        return delete(Stream.of(key).filter(StringUtils::isNotBlank).collect(Collectors.toList()));
    }
}
