package com.framework.cloud.job.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Xxl-job配置文件
 *
 * @author wusiwei
 */
@Data
@ConfigurationProperties(prefix = "framework.xxl.job")
public class XxlJobProperties {

    /**
     * 安全
     */
    private String accessToken;
    /**
     * xxl 服务器地址
     */
    private String adminAddresses;
    /**
     * 执行器
     */
    private ExecutorProperties executor;

    @Data
    public static class ExecutorProperties {

        /**
         * 执行器名称
         */
        private String appName;

        /**
         * 自动注册不需要 地址 ip:port
         */
        private String address;

        /**
         * 自动注册不需要
         */
        private String ip;

        /**
         * 端口
         */
        private Integer port;

        /**
         * 日志路径
         */
        private String logPath;

        /**
         * 日志保留天数
         */
        private Integer logRetentionDays = 30;

    }
}
