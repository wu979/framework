package com.framework.cloud.holder.constant;

/**
 * @author wusiwei
 */
public class OauthConstant {

    public static final String CREDENTIALS = "N/A";
    public static final String APPLICATION_JSON = "application/json;charset=UTF-8";
    /**
     * 授权码长度
     */
    public static final int CODE_RANDOM = 16;
    /**
     * 令牌类型
     */
    public static final String GRANT_TYPE = "grant_type";
    /**
     * 返回类型
     */
    public static final String RESPONSE_TOKEN = "token";
    /**
     * 刷新模式
     */
    public static final String REFRESH_TOKEN = "refresh_token";
    /**
     * 登录名
     */
    public static final String USERNAME = "username";
    /**
     * 密码模式
     */
    public static final String PASSWORD = "password";
    /**
     * 第三方模式
     */
    public static final String OPEN_ID = "open_id";
    /**
     * 客户端ID
     */
    public static final String CLIENT_ID = "client_id";
    /**
     * 授权码
     */
    public static final String AUTHENTICATION_CODE = "code";
    /**
     * 回调地址
     */
    public static final String REDIRECT_URI = "redirect_uri";

    /**
     * 自定义令牌 用户信息上下文
     */
    public static final String USER_DETAIL = "user_detail";

    /**
     * 自定义令牌 用户名称上下文
     */
    public static final String USER_NAME = "user_name";

    /**
     * 自定义令牌 用户权限上下文
     */
    public static final String AUTHORITIES = "authorities";
    /**
     * 授权码获取成功参数
     */
    public static final String CODE_PARAM = "{0}?appKey={1}&code={2}";
    /**
     * 授权码获取成功参数
     */
    public static final String CODE_PARAM_STATE = "{0}?appKey={1}&code={2}&state={3}";
}
