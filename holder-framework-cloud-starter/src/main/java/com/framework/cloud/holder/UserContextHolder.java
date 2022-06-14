package com.framework.cloud.holder;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.framework.cloud.holder.model.LoginUser;

/**
 * 认证用户上下文传递
 *
 * @author wusiwei
 */
public class UserContextHolder {

    private final ThreadLocal<LoginUser> userContext;

    private static class SingletonContext {
        private static final UserContextHolder INSTANCE = new UserContextHolder();
    }

    private UserContextHolder() {
        this.userContext = new TransmittableThreadLocal<>();
    }

    public static UserContextHolder getInstance() {
        return SingletonContext.INSTANCE;
    }

    public void setUser(LoginUser userDetail) {
        userContext.set(userDetail);
    }

    public LoginUser getUser() {
        return userContext.get();
    }

    public void clear() {
        userContext.remove();
    }
}
