package com.framework.cloud.stream.annotation;

import lombok.Data;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.messaging.Message;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 *
 * @author wusiwei
 */
@Aspect
public class StreamAspect {

    @Around("@within(org.springframework.cloud.stream.annotation.StreamListener) || @annotation(org.springframework.cloud.stream.annotation.StreamListener)")
    public Object streamListener(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        return joinPoint.proceed();
    }

    private Object[] getRequestArgs(Object[] args) {
        Object[] printArgs = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if ((args[i] instanceof HttpServletRequest) || args[i] instanceof HttpServletResponse) {
                continue;
            }
            if (args[i] instanceof Message<?>) {

            } else {

            }
            if (args[i] instanceof byte[]) {
                printArgs[i] = "byte array";
            } else {
                printArgs[i] = args[i];
            }
        }
        return printArgs;
    }

    @Data
    public static class Print {



    }
}
