package com.framework.cloud.feign.filter;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.cloud.holder.TenantContextHolder;
import com.framework.cloud.holder.constant.HeaderConstant;
import com.framework.cloud.holder.model.LoginTenant;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.core.Ordered;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 租户上下文过滤器
 *
 * @author wusiwei
 */
@AllArgsConstructor
@ConditionalOnClass(Filter.class)
public class TenantFilter extends OncePerRequestFilter implements Ordered {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            //优先获取请求参数中的tenantId值
            String tenant = request.getHeader(HeaderConstant.X_TENANT_HEADER);
            //保存租户id
            if (StrUtil.isNotEmpty(tenant)) {
                LoginTenant loginTenant = objectMapper.readValue(Base64.decodeStr(tenant), LoginTenant.class);
                TenantContextHolder.getInstance().setTenant(loginTenant);
            }
            chain.doFilter(request, response);
        } finally {
            TenantContextHolder.getInstance().clear();
        }
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 2;
    }
}
