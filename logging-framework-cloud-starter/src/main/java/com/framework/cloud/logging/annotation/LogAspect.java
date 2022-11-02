package com.framework.cloud.logging.annotation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * global log aspect and trace ( {@link com.framework.cloud.logging.annotation.Log } or {@link LogAspect#log()}  } )
 *
 * @author wusiwei
 */
@Slf4j
@Aspect
@RequiredArgsConstructor
public class LogAspect {

    @Pointcut("execution(* com.framework.cloud.*.api.controller.*Controller.*(..))")
    public void log() {
    }

    @Before(value = "log()")
    public void logBefore() {
        String s = TraceContext.traceId();
        int i = TraceContext.spanId();
        String s1 = TraceContext.segmentId();
        System.out.println("11111111111111");
    }
}
