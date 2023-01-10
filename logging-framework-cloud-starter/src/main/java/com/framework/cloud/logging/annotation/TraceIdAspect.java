package com.framework.cloud.logging.annotation;

import cn.hutool.core.util.StrUtil;
import com.framework.cloud.common.utils.UUIDUtil;
import com.framework.cloud.holder.constant.HeaderConstant;
import com.framework.cloud.holder.utils.TraceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * global log aspect and trace ( {@link com.framework.cloud.logging.annotation.Log } or {@link TraceIdAspect#log()}  } )
 *
 * @author wusiwei
 */
@Slf4j
@Aspect
@RequiredArgsConstructor
@SuppressWarnings("all")
@Order(1)
public class TraceIdAspect {

    @Pointcut("execution(* com.framework.cloud.*.api.controller.*Controller.*(..))")
    public void traceId() {
    }

    @Before(value = "traceId()")
    public void traceIdBefore() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String traceId = request.getHeader(HeaderConstant.TRACE_ID);
        if (StrUtil.isEmpty(traceId)) {
            traceId = UUIDUtil.uuid();
        }
        MDC.put(HeaderConstant.TRACE_ID, traceId);
    }


    @After(value = "traceId()")
    public void traceIdAfter() {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();
            HttpServletResponse response = attributes.getResponse();
            response.addHeader(HeaderConstant.X_T_ID_HERDER, TraceUtil.tid());
            response.addHeader(HeaderConstant.X_TRACE_ID_HERDER, TraceUtil.traceId());
        } finally {
            MDC.remove(HeaderConstant.TRACE_ID);
        }
    }
}
