package com.framework.cloud.holder;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.framework.cloud.holder.model.LoginTenant;

/**
 * 认证用户租户上下文传递
 *
 * @author wusiwei
 */
public class TenantContextHolder {

    private final ThreadLocal<LoginTenant> tenantContext;

    private TenantContextHolder() {
        this.tenantContext = new TransmittableThreadLocal<>();
    }

    public static TenantContextHolder getInstance() {
        return TenantContextHolder.SingletonContext.INSTANCE;
    }

    private static class SingletonContext {
        private static final TenantContextHolder INSTANCE = new TenantContextHolder();
    }

    public void setTenant(LoginTenant tenant) {
        tenantContext.set(tenant);
    }

    public LoginTenant getTenant() {
        return tenantContext.get();
    }

    public void clear() {
        tenantContext.remove();
    }

}
