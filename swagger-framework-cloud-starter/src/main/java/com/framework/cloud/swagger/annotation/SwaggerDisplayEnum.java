package com.framework.cloud.swagger.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Swagger enum
 *
 * @author wusiwei
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SwaggerDisplayEnum {

    /**
     * 值
     */
    String name() default "name";

    /**
     * 文本
     */
    String label() default "label";

}
