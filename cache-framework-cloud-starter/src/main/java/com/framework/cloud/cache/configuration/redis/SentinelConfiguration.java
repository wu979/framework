package com.framework.cloud.cache.configuration.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * 哨兵 配置
 *
 * @author wusiwei
 */
@AllArgsConstructor
@Configuration(proxyBeanMethods = false)
@ConditionalOnMissingBean({RedisConfiguration.class})
@ConditionalOnProperty(prefix = "spring.redis", value = "type", havingValue = "sentinel")
public class SentinelConfiguration {

    private final ObjectMapper objectMapper;

    @Bean
    public RedisSentinelConfiguration sentinelRedisConfiguration(RedisProperties redisProperties) {
        RedisProperties.Sentinel sentinel = redisProperties.getSentinel();
        RedisSentinelConfiguration config = new RedisSentinelConfiguration();
        if (StringUtils.isNotBlank(redisProperties.getPassword())) {
            config.setPassword(RedisPassword.of(redisProperties.getPassword()));
        }
        config.setDatabase(redisProperties.getDatabase());
        config.master(sentinel.getMaster());
        config.setSentinels(createSentinels(sentinel.getNodes()));
        redisProperties.setCluster(null);
        return config;
    }

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient(RedisProperties redisProperties) {
        Config config = new Config();
        RedisProperties.Sentinel properties = redisProperties.getSentinel();
        List<String> nodes = properties.getNodes();
        List<String> newNodes = Lists.newArrayListWithCapacity(nodes.size());
        nodes.forEach((index) -> newNodes.add(
                index.startsWith("redis://") ? index : "redis://" + index));
        SentinelServersConfig sentinel = config.useSentinelServers()
                .addSentinelAddress(newNodes.toArray(new String[0]))
                .setMasterName(properties.getMaster())
                .setMasterConnectionPoolSize(250)
                .setSlaveConnectionPoolSize(250);
        if (StringUtils.isNotBlank(redisProperties.getPassword())) {
            sentinel.setPassword(redisProperties.getPassword());
        }
        config.setCodec(new JsonJacksonCodec(objectMapper));
        return Redisson.create(config);
    }

    private static List<RedisNode> createSentinels(List<String> nodes) {
        List<RedisNode> redisNodeList = new ArrayList<>();
        boolean flag = nodes.stream().allMatch(node -> node.contains(":"));
        Assert.isTrue(flag, "Redis哨兵模式：地址不合法!");
        nodes.forEach(node -> {
            String[] parts = node.split(":");
            redisNodeList.add(new RedisNode(parts[0], Integer.parseInt(parts[1])));
        });
        return redisNodeList;
    }
}
