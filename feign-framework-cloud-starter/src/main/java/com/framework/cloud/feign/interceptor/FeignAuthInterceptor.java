package com.framework.cloud.feign.interceptor;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.framework.cloud.common.utils.FastJsonUtil;
import com.framework.cloud.common.utils.StringUtil;
import com.framework.cloud.holder.TenantContextHolder;
import com.framework.cloud.holder.TokenContextHolder;
import com.framework.cloud.holder.UserContextHolder;
import com.framework.cloud.holder.UserRoleContextHolder;
import com.framework.cloud.holder.constant.HeaderConstant;
import com.framework.cloud.holder.constant.OauthConstant;
import com.framework.cloud.holder.model.LoginTenant;
import com.framework.cloud.holder.model.LoginUser;
import com.google.common.collect.Lists;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.seata.core.context.RootContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

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
        String token = request.getHeader(HeaderConstant.AUTHORIZATION);
        if (StringUtil.isBlank(token)) {
            token = request.getParameter(OauthConstant.ACCESS_TOKEN);
            if (StringUtil.isBlank(token)) {
                token = TokenContextHolder.getInstance().getToken();
            }
        }
        //传递 用户
        String user = request.getHeader(HeaderConstant.X_USER_HEADER);
        if (StringUtils.isBlank(user)) {
            LoginUser userDetail = UserContextHolder.getInstance().getUser();
            if (ObjectUtil.isNotNull(userDetail)) {
                user = FastJsonUtil.toJSONString(userDetail);
            }
        }
        //传递 租户
        String tenant = request.getHeader(HeaderConstant.X_TENANT_HEADER);
        if (StringUtils.isBlank(tenant)) {
            LoginTenant tenantDetail = TenantContextHolder.getInstance().getTenant();
            if (ObjectUtil.isNotNull(tenantDetail)) {
                tenant = FastJsonUtil.toJSONString(tenantDetail);
            }
        }
        //传递 用户权限
        String role = request.getHeader(HeaderConstant.X_AUTHORITIES_HEADER);
        if (StringUtils.isBlank(role)) {
            Set<String> userRole = UserRoleContextHolder.getInstance().getRoleList();
            if (CollectionUtil.isNotEmpty(userRole)) {
                role = CollectionUtil.join(userRole, ",");
            }
        }
        //传递 事务ID
        String xid = request.getHeader(RootContext.KEY_XID);
        if (StringUtils.isBlank(xid)) {
            xid = RootContext.getXID();
        }
        String traceId = request.getHeader(HeaderConstant.TRACE_ID);
        if (StringUtils.isBlank(traceId)) {
            traceId = MDC.get(HeaderConstant.TRACE_ID);
        }
        requestTemplate.header(HeaderConstant.AUTHORIZATION, token);
        requestTemplate.header(HeaderConstant.X_USER_HEADER, user);
        requestTemplate.header(HeaderConstant.X_TENANT_HEADER, tenant);
        requestTemplate.header(HeaderConstant.X_AUTHORITIES_HEADER, role);
        requestTemplate.header(HeaderConstant.TRACE_ID, traceId);
        requestTemplate.header(RootContext.KEY_XID, Lists.newArrayList(xid));
    }

}
