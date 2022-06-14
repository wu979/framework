package com.framework.cloud.feign.interceptor;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.framework.cloud.common.constant.HeaderConstant;
import com.framework.cloud.holder.TenantContextHolder;
import com.framework.cloud.holder.UserContextHolder;
import com.framework.cloud.holder.UserRoleContextHolder;
import com.framework.cloud.holder.model.LoginTenant;
import com.framework.cloud.holder.model.LoginUser;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: wsw-starter-cloud
 * @description: Feign 拦截器传递数据给下游服务 包含基础数据
 * @author: wsw
 * @create: 2021-09-28 17:32
 **/
public class FeignAuthInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        if (attributes == null) {
            return;
        }
        HttpServletRequest request = attributes.getRequest();
        //传递 用户
        String user = request.getHeader(HeaderConstant.X_USER_HEADER);
        if (StringUtils.isBlank(user)) {
            LoginUser userDetail = UserContextHolder.getInstance().getUser();
            if (ObjectUtil.isNotNull(userDetail)) {
                requestTemplate.header(HeaderConstant.X_USER_HEADER, JSON.toJSONString(userDetail));
            }
        }
        //传递 租户
        String tenant = request.getHeader(HeaderConstant.X_TENANT_HEADER);
        if (StringUtils.isBlank(tenant)) {
            LoginTenant tenantDetail = TenantContextHolder.getInstance().getTenant();
            if (ObjectUtil.isNotNull(tenantDetail)) {
                requestTemplate.header(HeaderConstant.X_TENANT_HEADER, JSON.toJSONString(tenantDetail));
            }
        }
        //传递 用户权限
        String role = request.getHeader(HeaderConstant.X_AUTHORITIES_HEADER);
        if (StringUtils.isBlank(role)) {
            List<String> userRole = UserRoleContextHolder.getInstance().getRoleList();
            if (CollectionUtil.isNotEmpty(userRole)) {
                requestTemplate.header(HeaderConstant.X_AUTHORITIES_HEADER, CollectionUtil.join(userRole, ","));
            }
        }
    }

}
