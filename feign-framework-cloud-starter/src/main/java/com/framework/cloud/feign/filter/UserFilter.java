package com.framework.cloud.feign.filter;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.cloud.holder.UserContextHolder;
import com.framework.cloud.holder.UserRoleContextHolder;
import com.framework.cloud.holder.constant.HeaderConstant;
import com.framework.cloud.holder.model.LoginUser;
import com.google.common.collect.Sets;
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
import java.util.Set;

/**
 * 用户上下文过滤器
 *
 * @author wusiwei
 */
@AllArgsConstructor
@ConditionalOnClass(Filter.class)
public class UserFilter extends OncePerRequestFilter implements Ordered {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            //传递用户
            String user = request.getHeader(HeaderConstant.X_USER_HEADER);
            //保存用户信息
            if (StrUtil.isNotEmpty(user)) {
                LoginUser loginUser = objectMapper.readValue(Base64.decodeStr(user), LoginUser.class);
                UserContextHolder.getInstance().setUser(loginUser);
            }
            String role = request.getHeader(HeaderConstant.X_AUTHORITIES_HEADER);
            //保存用户角色
            if (StrUtil.isNotEmpty(role)) {
                Set<String> roleList = Sets.newHashSet(StrUtil.splitTrim(Base64.decodeStr(role), ","));
                UserRoleContextHolder.getInstance().setRoleList(roleList);
            }
            chain.doFilter(request, response);
        } finally {
            UserContextHolder.getInstance().clear();
            UserRoleContextHolder.getInstance().clear();
        }
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 2;
    }

}
