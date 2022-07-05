package com.framework.cloud.elasticsearch.annotation;

import java.lang.annotation.*;

/**
 * @author wusiwei
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticDeclare {

    /**
     * 索引
     */
    String indexName() default "";

    /**
     * 类型
     */
    String indexType() default  "";
}
