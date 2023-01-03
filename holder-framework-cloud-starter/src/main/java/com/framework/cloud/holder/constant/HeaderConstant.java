package com.framework.cloud.holder.constant;

/**
 * 认证 常量
 *
 * @author wusiwei
 */
public class HeaderConstant {

    /**
     * 令牌正则解析取值
     */
    public static final String TOKEN = "token";
    /**
     * 令牌前缀
     */
    public static final String BEARER = "Bearer ";
    /**
     * 令牌
     */
    public static final String AUTHORIZATION = "Authorization";
    /**
     * 链路
     */
    public static final String TRACE_ID = "traceId";
    /**
     * 租户信息头
     */
    public static final String X_TENANT_HEADER = "x-tenant-header";
    /**
     * 用户信息头
     */
    public static final String X_USER_HEADER = "x-user-header";
    /**
     * 用户ID信息头
     */
    public static final String X_USER_ID_HEADER = "x-user-id-header";
    /**
     * 用户权限头
     */
    public static final String X_AUTHORITIES_HEADER = "x-authorities-header";
    /**
     * 链路头
     */
    public static final String X_T_ID_HERDER = "x-tid-header";
    /**
     * 链路头
     */
    public static final String X_TRACE_ID_HERDER = "x-trace-id-header";
}
