package com.framework.cloud.holder.constant;

/**
 * 认证缓存
 *
 * @author wusiwei
 */
public class CacheConstant {
    /**
     * 授权令牌 (缓存key)
     */
    public static final String ACCESS_TOKEN = "token:access:";
    /**
     * 刷新令牌 (缓存key)
     */
    public static final String REFRESH_TOKEN = "token:refresh:";
    /**
     * 授权令牌绑定刷新令牌 (缓存key)
     */
    public static final String ACCESS_REFRESH = "token:access:refresh:";
    /**
     * 租户最大认证次数
     */
    public static final String TENANT_COUNT = "tenant:count:";
    /**
     * 租户用户最大认证次数
     */
    public static final String TENANT_USER_COUNT = "tenant:user:count:";
}
