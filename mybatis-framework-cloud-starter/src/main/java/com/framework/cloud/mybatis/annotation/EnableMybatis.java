package com.framework.cloud.mybatis.annotation;

import com.framework.cloud.mybatis.MyBatisAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mybatis启动器
 *
 * @author wusiwei
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(MyBatisAutoConfiguration.class)
public @interface EnableMybatis {
}
