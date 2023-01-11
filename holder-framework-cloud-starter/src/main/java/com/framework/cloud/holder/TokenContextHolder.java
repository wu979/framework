package com.framework.cloud.holder;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * 令牌上下文传递
 *
 * @author wusiwei
 */
public class TokenContextHolder {

    private final ThreadLocal<String> tokenContext;

    private TokenContextHolder() {
        this.tokenContext = new TransmittableThreadLocal<>();
    }

    public static TokenContextHolder getInstance() {
        return TokenContextHolder.SingletonContext.INSTANCE;
    }

    private static class SingletonContext {
        private static final TokenContextHolder INSTANCE = new TokenContextHolder();
    }

    public void setToken(String token) {
        tokenContext.set(token);
    }

    public String getToken() {
        return tokenContext.get();
    }

    public void clear() {
        tokenContext.remove();
    }
}
