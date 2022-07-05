package com.framework.cloud.elasticsearch.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author wusiwei
 */
@Data
@ConfigurationProperties(prefix = "elasticsearch")
public class ElasticsearchProperties {

    /**
     * ip:port
     */
    private List<String> uris;
    /**
     * 账号
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 协议
     */
    private String schema;
    /**
     * 连接超时时间
     */
    private int connectTimeOut;
    /**
     * 连接超时时间
     */
    private int socketTimeOut;
    /**
     * 获取连接的超时时间
     */
    private int connectionRequestTimeOut;
    /**
     * 最大连接数
     */
    private int maxConnectNum;
    /**
     * 最大路由连接数
     */
    private int maxConnectPerRoute;
}
