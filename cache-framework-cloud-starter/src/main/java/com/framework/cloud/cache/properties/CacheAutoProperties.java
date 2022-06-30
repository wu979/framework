package com.framework.cloud.cache.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.concurrent.TimeUnit;

/**
 * @author wusiwei
 */
@Data
@ConfigurationProperties(prefix = "spring.redis")
public class CacheAutoProperties {

    /**
     * redis key 前缀 按项目区分
     */
    private String prefix;

    /**
     * 默认缓存过期时间
     */
    private Long cacheTimeout;

    /**
     * 默认缓存过期时间单位
     */
    private TimeUnit cacheTimeoutUnit;

    /**
     * 布隆过期器
     */
    private BloomFilterProperties bloomFilter;

    /**
     * 本地缓存
     */
    private CaffeineCache caffeineCache;

    @Data
    public static class BloomFilterProperties {

        /**
         * 布隆过滤器实例名称
         */
        private String name;

        /**
         * 每个元素的预期插入量
         */
        private Long expectedInsertions;

        /**
         * 预期错误概率
         */
        private Double falseProbability;

    }

    @Data
    public static class CaffeineCache {

        /**
         * 缓存名称
         */
        private String name;

        /**
         * 初始化容量
         */
        private Integer initialCapacity;

        /**
         * 最大数量
         */
        private Integer maximumSize;
    }
}
