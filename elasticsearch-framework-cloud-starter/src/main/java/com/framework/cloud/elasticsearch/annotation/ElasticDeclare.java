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
     * 分片
     */
    short shards() default 1;

    /**
     * 副本
     */
    short replicas() default 1;
}
