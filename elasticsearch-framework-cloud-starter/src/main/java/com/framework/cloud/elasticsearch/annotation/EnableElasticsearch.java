package com.framework.cloud.elasticsearch.annotation;

import com.framework.cloud.elasticsearch.ElasticsearchConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ES检索启动器
 *
 * @author wusiwei
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(ElasticsearchConfiguration.class)
public @interface EnableElasticsearch {
}
