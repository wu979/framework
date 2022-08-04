package com.framework.cloud.holder;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.Set;

/**
 * 认证用户角色上下文传递
 *
 * @author wusiwei
 */
public class UserRoleContextHolder {

    private final ThreadLocal<Set<String>> roleContext;

    private UserRoleContextHolder() {
        this.roleContext = new TransmittableThreadLocal<>();
    }

    public static UserRoleContextHolder getInstance() {
        return UserRoleContextHolder.SingletonContext.INSTANCE;
    }

    private static class SingletonContext {
        private static final UserRoleContextHolder INSTANCE = new UserRoleContextHolder();
    }

    public void setRoleList(Set<String> roleList) {
        roleContext.set(roleList);
    }

    public Set<String> getRoleList() {
        return roleContext.get();
    }

    public void clear() {
        roleContext.remove();
    }

}
