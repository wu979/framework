package com.framework.cloud.feign.filter;

import cn.hutool.core.util.StrUtil;
import com.framework.cloud.holder.TokenContextHolder;
import com.framework.cloud.holder.constant.HeaderConstant;
import com.framework.cloud.holder.constant.OauthConstant;
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
 * 令牌上下文过滤器
 *
 * @author wusiwei
 */
@AllArgsConstructor
@ConditionalOnClass(Filter.class)
public class TokenFilter extends OncePerRequestFilter implements Ordered {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            //传递令牌
            String token = request.getHeader(HeaderConstant.AUTHORIZATION);
            if (StrUtil.isEmpty(token)) {
                token = request.getParameter(OauthConstant.ACCESS_TOKEN);
            }
            //保存令牌
            if (StrUtil.isNotEmpty(token)) {
                TokenContextHolder.getInstance().setToken(token);
            }
            chain.doFilter(request, response);
        } finally {
            TokenContextHolder.getInstance().clear();
        }
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }
}
