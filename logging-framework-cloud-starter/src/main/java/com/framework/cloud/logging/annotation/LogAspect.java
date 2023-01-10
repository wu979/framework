package com.framework.cloud.logging.annotation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * print log
 *
 * @author wusiwei
 */
@Slf4j
@Aspect
@RequiredArgsConstructor
@SuppressWarnings("all")
public class LogAspect {

    /**
     * 打印类或方法上的 {@link Log}
     */
    @Around("@within(com.framework.cloud.logging.annotation.Log) || @annotation(com.framework.cloud.logging.annotation.Log)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        Object obj = null;
        try {
            obj = joinPoint.proceed();
        } finally {

        }
        return obj;
    }


    @Getter
    @Setter
    @RequiredArgsConstructor
    public static class Print {

        private String url;

        private String notes;

        /**
         * 开始时间
         */
        private String start;

        /**
         * 耗时
         */
        private String time;

        private Object[] request;

        private Object response;

    }
}
