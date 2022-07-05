package com.framework.cloud.elasticsearch.proxy;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.QueryBuilder;

import java.util.List;

/**
 * @author wusiwei
 */
public interface Elastic {

    <T> ElasticResponse<T> getById(String id, Class<T> clz);

    <T> ElasticResponse<T> queryOne(QueryBuilder queryBuilder, Class<T> clz);

    <T> ElasticResponse<List<T>> queryList(QueryBuilder  queryBuilder, Class<T> clz);

    <T> ElasticResponse<List<T>> queryList(SearchRequest searchRequest, Class<T> clz);

    <T> ElasticResponse<Long> getTotalCount(QueryBuilder queryBuilder, Class<T> clz);

    <T> ElasticResponse<Boolean> save(T t);

    default <T> ElasticResponse<T> getById(Long id, Class<T> clz) {
        return getById(String.valueOf(id), clz);
    }
}
