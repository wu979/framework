package com.framework.cloud.cache.configuration.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisPassword;

import java.util.List;
import java.util.Objects;

/**
 * @author wusiwei
 */
@AllArgsConstructor
@Configuration(proxyBeanMethods = false)
@ConditionalOnMissingBean({RedisConfiguration.class})
@ConditionalOnProperty(prefix = "spring.redis", value = "type", havingValue = "cluster")
public class ClusterConfiguration {

    private final ObjectMapper objectMapper;

    @Bean
    public RedisClusterConfiguration clusterRedisConfiguration(RedisProperties redisProperties) {
        RedisProperties.Cluster cluster = redisProperties.getCluster();
        RedisClusterConfiguration config = new RedisClusterConfiguration(cluster.getNodes());
        if (StringUtils.isNotBlank(redisProperties.getPassword())) {
            config.setPassword(RedisPassword.of(redisProperties.getPassword()));
        }
        if (Objects.nonNull(cluster.getMaxRedirects())) {
            config.setMaxRedirects(cluster.getMaxRedirects());
        }
        redisProperties.setSentinel(null);
        return config;
    }

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient(RedisProperties redisProperties) {
        Config config = new Config();
        List<String> nodes = redisProperties.getCluster().getNodes();
        List<String> newNodes = Lists.newArrayListWithCapacity(nodes.size());
        nodes.forEach((index) -> newNodes.add(
                index.startsWith("redis://") ? index : "redis://" + index));
        ClusterServersConfig cluster = config.useClusterServers()
                .addNodeAddress(newNodes.toArray(new String[0]));
        if (StringUtils.isNotBlank(redisProperties.getPassword())) {
            cluster.setPassword(redisProperties.getPassword());
        }
        config.setCodec(new JsonJacksonCodec(objectMapper));
        return Redisson.create(config);
    }
}
