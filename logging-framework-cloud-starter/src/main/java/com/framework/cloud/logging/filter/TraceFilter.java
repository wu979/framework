package com.framework.cloud.logging.filter;

import com.framework.cloud.holder.constant.HeaderConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * skywalking traceId show on response header
 *
 * @author wusiwei
 */
@Slf4j
@ConditionalOnProperty(value = "skywalking.traceId.header", havingValue = "true")
public class TraceFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try{
            response.addHeader(HeaderConstant.X_TRACE_ID_HERDER, TraceContext.traceId());
        }catch (Exception e) {
            log.error("skywalking traceId set error");
        }
        chain.doFilter(request, response);
    }
}
