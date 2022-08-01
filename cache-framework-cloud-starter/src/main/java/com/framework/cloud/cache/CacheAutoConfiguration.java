package com.framework.cloud.cache;

import com.framework.cloud.cache.annotation.CacheAspect;
import com.framework.cloud.cache.annotation.LockAspect;
import com.framework.cloud.cache.cache.LocalCache;
import com.framework.cloud.cache.cache.RedisCache;
import com.framework.cloud.cache.configuration.CaffeineConfiguration;
import com.framework.cloud.cache.configuration.RedisConfiguration;
import com.framework.cloud.cache.configuration.ZookeeperConfiguration;
import com.framework.cloud.cache.lock.RedisDistributedLock;
import com.framework.cloud.cache.lock.ZkDistributedLock;
import com.framework.cloud.cache.properties.CacheAutoProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * Introduce this configuration class to enable caching
 *
 * @author wusiwei
 */
@EnableCaching
@Import({RedisConfiguration.class, ZookeeperConfiguration.class, CaffeineConfiguration.class})
public class CacheAutoConfiguration {

    @Bean
    @ConditionalOnBean({LocalCache.class, RedisCache.class})
    public CacheAspect cacheAspect(LocalCache localCache, RedisCache redisCache, CacheAutoProperties cacheAutoProperties) {
        return new CacheAspect(localCache, redisCache, cacheAutoProperties);
    }

    @Bean
    @ConditionalOnBean({RedisDistributedLock.class, ZkDistributedLock.class})
    public LockAspect lockAspect(RedisDistributedLock redisDistributedLock, ZkDistributedLock zkDistributedLock) {
        return new LockAspect(redisDistributedLock, zkDistributedLock);
    }
}
