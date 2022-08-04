package com.framework.cloud.holder.utils;

import cn.hutool.core.collection.CollectionUtil;
import com.framework.cloud.common.exception.OauthException;
import com.framework.cloud.common.result.Result;
import com.framework.cloud.common.utils.StringUtil;
import com.framework.cloud.holder.TenantContextHolder;
import com.framework.cloud.holder.UserContextHolder;
import com.framework.cloud.holder.UserRoleContextHolder;
import com.framework.cloud.holder.constant.HeaderConstant;
import com.framework.cloud.holder.constant.OauthConstant;
import com.framework.cloud.holder.feign.OauthHolderFeignService;
import com.framework.cloud.holder.model.LoginTenant;
import com.framework.cloud.holder.model.LoginUser;
import com.framework.cloud.holder.vo.AuthorizationLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * 认证信息
 *
 * @author wusiwei
 */
@Component
public class OauthUtil {

    private static OauthHolderFeignService oauthHolderFeignService;

    @Autowired
    public OauthUtil(OauthHolderFeignService oauthHolderFeignService) {
        OauthUtil.oauthHolderFeignService = oauthHolderFeignService;
    }

    /**
     * 获取认证用户
     *
     * @return 用户
     */
    public static LoginUser getUser() {
        LoginUser loginUser = UserContextHolder.getInstance().getUser();
        if (null == loginUser) {
            loginUser = loginUser();
            if (null == loginUser) {
                throw new OauthException();
            }
        }
        return loginUser;
    }

    /**
     * 获取认证用户ID
     *
     * @return 用户ID
     */
    public static Long getUserId() {
        return getUser().getId();
    }

    /**
     * 获取认证租户
     *
     * @return 租户
     */
    public static LoginTenant getTenant() {
        LoginTenant loginTenant = TenantContextHolder.getInstance().getTenant();
        if (null == loginTenant) {
            loginTenant = loginTenant();
            if (null == loginTenant) {
                throw new OauthException();
            }
        }
        return loginTenant;
    }

    /**
     * 获取认证租户ID
     *
     * @return 租户ID
     */
    public static Long getTenantId() {
        return getTenant().getId();
    }

    /**
     * 获取认证用户角色
     *
     * @return 角色列表
     */
    public static Set<String> getRoleList() {
        Set<String> roleList = UserRoleContextHolder.getInstance().getRoleList();
        if (CollectionUtil.isEmpty(roleList)) {
            roleList = roleList();
            if (CollectionUtil.isEmpty(roleList)) {
                throw new OauthException();
            }
        }
        return roleList;
    }


    private static LoginUser loginUser() {
        return oauth() ? UserContextHolder.getInstance().getUser() : null;
    }

    private static LoginTenant loginTenant() {
        return oauth() ? TenantContextHolder.getInstance().getTenant() : null;
    }

    private static Set<String> roleList() {
        return oauth() ? UserRoleContextHolder.getInstance().getRoleList() : null;
    }

    private static boolean oauth() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null == requestAttributes) {
            return false;
        }
        HttpServletRequest request = requestAttributes.getRequest();
        String authorization = request.getHeader(HeaderConstant.AUTHORIZATION);
        if (StringUtil.isBlank(authorization)) {
            authorization = request.getParameter(OauthConstant.ACCESS_TOKEN);
            if (StringUtil.isBlank(authorization)) {
                return false;
            }
        }
        Result<AuthorizationLoginVO> result = oauthHolderFeignService.loginUser(authorization);
        if (!result.success()) {
            return false;
        }
        AuthorizationLoginVO data = result.getData();
        if (null == data) {
            return false;
        }
        UserContextHolder.getInstance().setUser(data.getLoginUser());
        TenantContextHolder.getInstance().setTenant(data.getLoginTenant());
        UserRoleContextHolder.getInstance().setRoleList(data.getRoleList());
        return true;
    }
}
