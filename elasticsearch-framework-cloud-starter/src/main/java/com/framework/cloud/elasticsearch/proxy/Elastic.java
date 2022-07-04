package com.framework.cloud.elasticsearch.proxy;

import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.util.List;

/**
 * @author wusiwei
 */
public interface Elastic {

    <T> T getById(Long id, Class<T> clz);

    <T> T queryOne(SearchSourceBuilder sourceBuilder, Class<T> clz);

    <T> List<T> queryList(SearchSourceBuilder sourceBuilder, Class<T> clz);

}
