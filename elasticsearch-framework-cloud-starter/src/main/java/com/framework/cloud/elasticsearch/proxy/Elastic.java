package com.framework.cloud.elasticsearch.proxy;

import com.framework.cloud.elasticsearch.utils.ElasticUtil;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.QueryBuilder;

import java.util.List;

/**
 * @author wusiwei
 */
public interface Elastic {

    /**
     * 索引是否存在
     * @param indexName 索引名
     * @return bool
     */
    ElasticResponse<Boolean> isExists(String indexName);

    /**
     * 创建索引
     * @param indexName 索引名
     * @param source 数据源
     * @return ok
     */
    <T> ElasticResponse<Boolean> createIndex(String indexName, Class<T> source);

    <T> ElasticResponse<Boolean> save(T t);

    <T> ElasticResponse<T> getById(String id, Class<T> clz);

    <T> ElasticResponse<T> queryOne(QueryBuilder queryBuilder, Class<T> clz);

    <T> ElasticResponse<List<T>> queryList(QueryBuilder  queryBuilder, Class<T> clz);

    <T> ElasticResponse<List<T>> queryList(SearchRequest searchRequest, Class<T> clz);

    <T> ElasticResponse<Long> getTotalCount(QueryBuilder queryBuilder, Class<T> clz);

    default <T> ElasticResponse<T> getById(Long id, Class<T> clz) {
        return getById(String.valueOf(id), clz);
    }

    default <T> ElasticResponse<Boolean> createIndex(Class<T> source) {
        return createIndex(ElasticUtil.indexName(source), source);
    }

    default <T> ElasticResponse<Boolean> isExists(Class<T> source) {
        return isExists(ElasticUtil.indexName(source));
    }
}
