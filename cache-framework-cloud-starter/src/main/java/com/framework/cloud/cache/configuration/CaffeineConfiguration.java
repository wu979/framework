package com.framework.cloud.cache.configuration;

import com.framework.cloud.cache.cache.LocalCache;
import com.framework.cloud.cache.cache.LocalCacheTemplate;
import com.framework.cloud.cache.properties.CacheAutoProperties;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.context.annotation.Bean;

/**
 * Initialize the caffeine type as local cache
 *
 * @author wusiwei
 */
@AllArgsConstructor
@EnableConfigurationProperties(CacheAutoProperties.class)
public class CaffeineConfiguration {

    private final CacheAutoProperties cacheAutoProperties;

    @Bean
    public LocalCache localCache(CaffeineCache caffeineCache) {
        return new LocalCacheTemplate(caffeineCache.getNativeCache());
    }

    @Bean
    public CaffeineCache caffeineCache() {
        return new CaffeineCache(cacheAutoProperties.getCaffeineCache().getName(), cache());
    }

    private Cache<Object, Object> cache() {
        return Caffeine
                .newBuilder()
                .expireAfterWrite(cacheAutoProperties.getCacheTimeout(), cacheAutoProperties.getCacheTimeoutUnit())
                .initialCapacity(cacheAutoProperties.getCaffeineCache().getInitialCapacity())
                .maximumSize(cacheAutoProperties.getCaffeineCache().getMaximumSize())
                .build();
    }
}
