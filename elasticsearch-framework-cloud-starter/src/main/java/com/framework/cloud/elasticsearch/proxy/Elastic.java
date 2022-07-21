package com.framework.cloud.elasticsearch.proxy;

import com.framework.cloud.common.base.BasePage;
import com.framework.cloud.common.base.PageVO;
import com.framework.cloud.elasticsearch.utils.ElasticUtil;
import lombok.NonNull;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;

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
    ElasticResponse<Boolean> isExists(@NonNull String indexName);

    /**
     * 创建索引
     * @param indexName 索引名
     * @param source 数据源
     * @return bool
     */
    <T> ElasticResponse<Boolean> createIndex(@NonNull String indexName, @NonNull Class<T> source);

    /**
     * 删除索引
     * @param indexName 索引名
     */
    <T> ElasticResponse<Boolean> deleteIndex(@NonNull String indexName);

    /**
     * 根据ID删除
     * @param indexName 索引名
     * @param id 主键
     */
    ElasticResponse<Boolean> deleteById(@NonNull String indexName, @NonNull String id);

    /**
     * 根据ID删除
     * @param indexName 索引名
     * @param ids 主键
     */
    ElasticResponse<Boolean> deleteById(@NonNull String indexName, @NonNull List<String> ids);

    /**
     * 删除文档
     * @param indexName 索引名
     * @param query 删除条件
     * @return 失败id
     */
    <T> ElasticResponse<List<String>> deleteDocument(@NonNull String indexName, @NonNull QueryBuilder query, @NonNull Class<T> clz);

    /**
     * 新增
     * @param indexName 索引名
     * @param source 数据源
     * @return bool
     */
    <T> ElasticResponse<Boolean> save(@NonNull String indexName, @NonNull T source);

    /**
     * 批量新增
     * @param indexName 索引名
     * @param sourceList 数据源
     * @return bool
     */
    <T> ElasticResponse<Boolean> save(@NonNull String indexName, @NonNull List<T> sourceList);

    /**
     * 根据ID 更新
     * @param indexName 索引名
     * @param source 数据源
     * @return bool
     */
    <T> ElasticResponse<Boolean> update(@NonNull String indexName, @NonNull T source);

    /**
     * 根据ID 批量更新
     * @param indexName 索引名
     * @param sourceList 数据源
     * @return bool
     */
    <T> ElasticResponse<Boolean> update(@NonNull String indexName, @NonNull List<T> sourceList);

    /**
     * 根据ID 更新
     * @param indexName 索引名
     * @param updateQuery 更新条件
     * @return bool
     */
    <T> ElasticResponse<Boolean> update(@NonNull String indexName, @NonNull UpdateQuery updateQuery);

    /**
     * 分页
     * @param indexName 索引名
     * @param request 请求参数 extends {@link BasePage }
     * @param source 文档
     * @param queryBuilder 查询条件
     * @param sortBuilders 排序
     * @return 分页
     */
    <T extends BasePage, R, S extends SortBuilder<S>> PageVO<R> page(@NonNull String indexName, QueryBuilder queryBuilder, @NonNull T request, @NonNull Class<R> source, @NonNull List<SortBuilder<S>> sortBuilders);

    /**
     * 分页
     * @param indexName 索引名
     * @param source 文档
     * @param query 查询条件
     * @return 分页
     */
    <R> PageVO<R> page(@NonNull String indexName, Query query, @NonNull Class<R> source);

    /**
     * 根据ID查询
     * @param indexName 索引名
     * @param id 主键
     * @param source 文档类型
     */
    <T> ElasticResponse<T> getById(@NonNull String indexName, @NonNull String id, @NonNull Class<T> source);

    /**
     * 查询列表
     * @param indexName 索引名
     * @param queryBuilder 查询条件
     * @param source 文档类型
     */
    <T> ElasticResponse<List<T>> queryList(@NonNull String indexName, QueryBuilder queryBuilder, @NonNull Class<T> source);

    /**
     * 查询文档总数
     * @param indexName 索引名
     * @param queryBuilder 查询条件
     */
    ElasticResponse<Long> getTotalCount(@NonNull String indexName, QueryBuilder queryBuilder);

    default <T> ElasticResponse<T> getById(@NonNull String indexName, @NonNull Long id, @NonNull Class<T> source) {
        return getById(indexName, String.valueOf(id), source);
    }

    default <T> ElasticResponse<T> getById(@NonNull String id, @NonNull Class<T> source) {
        return getById(ElasticUtil.indexName(source), id, source);
    }

    default <T> ElasticResponse<T> getById(@NonNull Long id, @NonNull Class<T> source) {
        return getById(ElasticUtil.indexName(source), String.valueOf(id), source);
    }

    default <T> ElasticResponse<Boolean> createIndex(@NonNull Class<T> source) {
        return createIndex(ElasticUtil.indexName(source), source);
    }

    default <T> ElasticResponse<Boolean> deleteIndex(@NonNull Class<T> source) {
        return deleteIndex(ElasticUtil.indexName(source));
    }

    default <T> ElasticResponse<Boolean> isExists(@NonNull Class<T> source) {
        return isExists(ElasticUtil.indexName(source));
    }

    default <T> ElasticResponse<Boolean> save(@NonNull T source) {
        return save(ElasticUtil.indexName(source), source);
    }

    default <T> ElasticResponse<Boolean> save(@NonNull List<T> sourceList) {
        return save(ElasticUtil.indexName(sourceList.get(0)), sourceList);
    }

    default <T> ElasticResponse<Boolean> update(@NonNull T source) {
        return update(ElasticUtil.indexName(source), source);
    }

    default <T> ElasticResponse<Boolean> update(@NonNull List<T> sourceList) {
        return update(ElasticUtil.indexName(sourceList.get(0)), sourceList);
    }

    default <T extends BasePage, R> PageVO<R> page(@NonNull String indexName, QueryBuilder queryBuilder, @NonNull T request, @NonNull Class<R> source) {
        List<SortBuilder<FieldSortBuilder>> sortBuilders = ElasticUtil.sortBuilders(request);
        return page(indexName, queryBuilder, request, source, sortBuilders);
    }
}
