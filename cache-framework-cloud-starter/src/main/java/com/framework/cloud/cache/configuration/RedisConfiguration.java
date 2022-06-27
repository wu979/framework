package com.framework.cloud.cache.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.cloud.cache.cache.RedisCache;
import com.framework.cloud.cache.cache.RedisCacheTemplate;
import com.framework.cloud.cache.configuration.redis.ClusterConfiguration;
import com.framework.cloud.cache.configuration.redis.SentinelConfiguration;
import com.framework.cloud.cache.configuration.redis.StandaloneConfiguration;
import com.framework.cloud.cache.lock.RedisDistributedLock;
import com.framework.cloud.cache.lock.RedisDistributedLockImpl;
import com.framework.cloud.cache.properties.RedisAutoProperties;
import com.framework.cloud.cache.serializer.StringRedisKeySerializer;
import lombok.AllArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * Initialize the reids type according to the type specification (standalone、sentinel、cluster)
 *
 * @author wusiwei
 */
@AllArgsConstructor
@AutoConfigureAfter({ObjectMapper.class, RedisConnectionFactory.class})
@EnableConfigurationProperties({RedisProperties.class, RedisAutoProperties.class})
@Import({StandaloneConfiguration.class, SentinelConfiguration.class, ClusterConfiguration.class})
public class RedisConfiguration {

    private final ObjectMapper objectMapper;
    private final RedisAutoProperties redisAutoProperties;

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        serializer.setObjectMapper(objectMapper);
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(30L))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer))
                .disableCachingNullValues();
        return RedisCacheManager
                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
                .cacheDefaults(config)
                .build();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer();
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisKeySerializer(redisAutoProperties.getPrefix()));
        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setHashKeySerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        redisTemplate.setHashValueSerializer(serializer);
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public RedissonConnectionFactory redissonConnectionFactory(RedissonClient redissonClient) {
        return new RedissonConnectionFactory(redissonClient);
    }

    @Bean
    public RedisDistributedLock redisDistributedLock(RedissonClient redissonClient) {
        return new RedisDistributedLockImpl(redissonClient);
    }

    @Bean
    public RBloomFilter<String> bloomFilter(RedissonClient redissonClient) {
        RedisAutoProperties.BloomFilterProperties bloomFilterProperties = redisAutoProperties.getBloomFilter();
        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter(bloomFilterProperties.getName());
        bloomFilter.tryInit(bloomFilterProperties.getExpectedInsertions(), bloomFilterProperties.getFalseProbability());
        return bloomFilter;
    }

    @Bean
    public RedisCache redisCache(RedisTemplate<String, Object> redisTemplate, RedisDistributedLock redisDistributedLock, RBloomFilter<String> bloomFilter) {
        return new RedisCacheTemplate(redisTemplate, redisDistributedLock, redisAutoProperties, bloomFilter);
    }

}
