package com.framework.cloud.elasticsearch.annotation;

import java.lang.annotation.*;

/**
 *
 *
 * @author wusiwei
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface ElasticId {
}
