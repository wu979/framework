package com.framework.cloud.core.annotation;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 微服务启动器
 *
 * @author wusiwei
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ComponentScan
@EnableFeignClients
@EnableAutoConfiguration
@EnableConfigurationProperties
public @interface FrameworkApplication {

    @AliasFor(
            annotation = ComponentScan.class,
            attribute = "basePackages"
    )
    String[] componentScan() default {"com.framework.cloud.**"};

    @AliasFor(
            annotation = EnableFeignClients.class,
            attribute = "basePackages"
    )
    String[] enableFeignClients() default {"com.framework.cloud.**.api.application.feign"};

}
