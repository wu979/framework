package com.framework.cloud.stream.annotation;

import com.framework.cloud.stream.StreamAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wusiwei
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(StreamAutoConfiguration.class)
public @interface EnableStream {
}
