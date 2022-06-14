package com.framework.cloud.job.annotation;

import com.framework.cloud.job.XxlJobConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Xxl-job启动器
 *
 * @author wusiwei
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(XxlJobConfiguration.class)
public @interface EnableXxlJob {
}
