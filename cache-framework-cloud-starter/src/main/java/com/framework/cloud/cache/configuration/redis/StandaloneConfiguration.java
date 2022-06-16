package com.framework.cloud.cache.configuration.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;

/**
 * 单机 配置
 *
 * @author wusiwei
 */
@AllArgsConstructor
@Configuration(proxyBeanMethods = false)
@ConditionalOnMissingBean({RedisConfiguration.class})
@ConditionalOnProperty(prefix = "spring.redis", value = "type", havingValue = "standalone")
public class StandaloneConfiguration {

    private final ObjectMapper objectMapper;

    @Bean
    public RedisStandaloneConfiguration standaloneRedisConfiguration(RedisProperties redisProperties) {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(redisProperties.getHost(), redisProperties.getPort());
        if (StringUtils.isNotBlank(redisProperties.getPassword())) {
            config.setPassword(RedisPassword.of(redisProperties.getPassword()));
        }
        config.setDatabase(redisProperties.getDatabase());
        redisProperties.setCluster(null);
        redisProperties.setSentinel(null);
        return config;
    }

    @Bean(destroyMethod = "shutdown")
    @ConditionalOnProperty(prefix = "spring.redis.lock-type.redis", value = "enable", havingValue = "true")
    public RedissonClient redissonClient(RedisProperties redisProperties) {
        String address = "redis://" + redisProperties.getHost() + ":" + redisProperties.getPort();
        Config config = new Config();
        SingleServerConfig single = config.useSingleServer()
                .setAddress(address)
                .setDatabase(redisProperties.getDatabase())
                .setConnectionMinimumIdleSize(redisProperties.getLettuce().getPool().getMinIdle());
        if (StringUtils.isNotBlank(redisProperties.getPassword())) {
            single.setPassword(redisProperties.getPassword());
        }
        config.setCodec(new JsonJacksonCodec(objectMapper));
        return Redisson.create(config);
    }
}
