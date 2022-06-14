package com.framework.cloud.holder.utils;

import com.framework.cloud.holder.TenantContextHolder;
import com.framework.cloud.holder.UserContextHolder;
import com.framework.cloud.holder.UserRoleContextHolder;
import com.framework.cloud.holder.model.LoginTenant;
import com.framework.cloud.holder.model.LoginUser;

import java.util.List;
import java.util.Objects;

/**
 * 认证信息
 *
 * @author wusiwei
 */
public class OauthUtil {

    /**
     * 获取认证用户
     *
     * @return 用户
     */
    public static LoginUser getUser() {
        return UserContextHolder.getInstance().getUser();
    }

    /**
     * 获取认证用户ID
     *
     * @return 用户ID
     */
    public static Long getUserId() {
        return UserContextHolder.getInstance().getUser().getId();
    }

    /**
     * 获取认证租户
     *
     * @return 租户
     */
    public static LoginTenant getTenant() {
        return TenantContextHolder.getInstance().getTenant();
    }

    /**
     * 获取认证租户ID
     *
     * @return 租户ID
     */
    public static Long getTenantId() {
        LoginTenant tenant = TenantContextHolder.getInstance().getTenant();
        if (Objects.isNull(tenant)) {
            LoginUser user = getUser();
            if (Objects.nonNull(user)) {
                return user.getTenantId();
            }
        }
        return tenant.getId();
    }

    /**
     * 获取认证用户角色
     *
     * @return 角色列表
     */
    public static List<String> getRoleList() {
        return UserRoleContextHolder.getInstance().getRoleList();
    }
}
