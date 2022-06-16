package com.framework.cloud.cache.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 缓存 配置中心
 *
 * @author wusiwei
 */
@Data
@ConfigurationProperties(prefix = "spring.redis")
public class RedisAutoProperties {

    /**
     * redis key 前缀 按项目区分
     */
    private String prefix;

    /**
     * redis type 单机、哨兵、集群
     */
    private String type;

}
