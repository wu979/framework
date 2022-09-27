package com.framework.cloud.feign.filter;

import com.framework.cloud.common.utils.StringUtil;
import io.seata.core.context.RootContext;
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
 * 分布式事务ID过滤器
 *
 * @author wusiwei
 */
@ConditionalOnClass(Filter.class)
public class SeataFilter extends OncePerRequestFilter implements Ordered {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            //传递XID
            String xid = request.getHeader(RootContext.KEY_XID);
            if (StringUtil.isNotBlank(xid)) {
                RootContext.bind(xid);
            }
            chain.doFilter(request, response);
        } finally {
            RootContext.unbind();
        }
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 3;
    }

}
