package com.framework.cloud.cache.cache;

import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * caffeine
 *
 * @author wusiwei
 */
public interface LocalCache extends MultistageCache {

    /**
     * 获取缓存
     *
     * @param cacheLoader 缓存加载
     */
    <T> T get(@NotBlank String key, Class<T> clz, CacheLoader<T> cacheLoader);

    /**
     * 获取缓存
     */
    <T> List<T> getAll(String key, Class<T> clz);

    /**
     * 加入缓存
     */
    boolean putAll(String key, List<Object> list);

    /**
     * 加入缓存 Map<Key, Value>
     */
    boolean putAll(Map<String, Object> map);

    /**
     * 加入缓存 Map<Suffix, Value>
     */
    boolean putAll(String prefix, Map<String, Object> map);

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
