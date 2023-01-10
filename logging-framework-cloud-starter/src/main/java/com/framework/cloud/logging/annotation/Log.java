package com.framework.cloud.logging.annotation;

import io.swagger.annotations.ApiOperation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * custom log comments
 *
 * @author wusiwei
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {

    /**
     * 打印入参
     */
    boolean request() default true;

    /**
     * 打印出餐
     */
    boolean response() default true;

    /**
     * "" 默认打印 {@link ApiOperation#value() } -> {@link ApiOperation#notes() } -> {@link Method#getName() }
     */
    String notes() default "";
}
