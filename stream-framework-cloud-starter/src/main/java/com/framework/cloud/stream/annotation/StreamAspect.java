package com.framework.cloud.stream.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 *
 * @author wusiwei
 */
@Aspect
public class StreamAspect {

    @Around("@within(org.springframework.cloud.stream.annotation.StreamListener) || " +
            "@annotation(org.springframework.cloud.stream.annotation.StreamListener)")
    public Object streamListener(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        return joinPoint.proceed();
    }

}
