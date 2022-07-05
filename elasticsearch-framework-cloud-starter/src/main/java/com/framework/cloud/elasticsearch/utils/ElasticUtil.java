package com.framework.cloud.elasticsearch.utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Pair;
import cn.hutool.core.util.ObjectUtil;
import com.framework.cloud.common.exception.ElasticException;
import com.framework.cloud.elasticsearch.annotation.ElasticDeclare;
import com.framework.cloud.elasticsearch.annotation.ElasticId;
import com.framework.cloud.elasticsearch.enums.ElasticMessage;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.QueryBuilder;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author wusiwei
 */
public class ElasticUtil {

    public static Pair<String, String> check(String id, Class<?> clz) {
        if (StringUtils.isBlank(id)) {
            throw new ElasticException(ElasticMessage.NOT_FOUND_ID.getMsg());
        }
        return check(clz.getAnnotation(ElasticDeclare.class), clz);
    }

    public static Pair<String, String> check(QueryBuilder queryBuilder, Class<?> clz) {
        if (ObjectUtil.isNull(queryBuilder)) {
            throw new ElasticException(ElasticMessage.REQUEST_NULL.getMsg());
        }
        return check(clz.getAnnotation(ElasticDeclare.class), clz);
    }

    public static Pair<String, String> check(SearchRequest searchRequest, Class<?> clz) {
        if (ObjectUtil.isNull(searchRequest)) {
            throw new ElasticException(ElasticMessage.REQUEST_NULL.getMsg());
        }
        return check(clz.getAnnotation(ElasticDeclare.class), clz);
    }

    /**
     * 检查并返回索引名称和索引类型
     *
     * @return Pair<indexName,indexType>
     */
    public static Pair<String, String> check(ElasticDeclare elasticDeclare, Class<?> clz) {
        if (null == clz) {
            throw new ElasticException(ElasticMessage.CLZ_NULL.getMsg());
        }
        if (null == elasticDeclare) {
            throw new ElasticException(ElasticMessage.NOT_FOUND_DECLARE.getMsg());
        }
        if (StringUtils.isBlank(elasticDeclare.indexName()) || StringUtils.isBlank(elasticDeclare.indexType())) {
            throw new ElasticException(ElasticMessage.INDEX_ERROR.getMsg());
        }
        return Pair.of(elasticDeclare.indexName(), elasticDeclare.indexType());
    }
    /**
     * 获取id的域
     */
    public static Field getIdField(Class<?> clz) {
        List<Field> listWithAnnotation = FieldUtils.getFieldsListWithAnnotation(clz, ElasticId.class);
        if (CollectionUtil.isEmpty(listWithAnnotation)) {
            return null;
        }
        return listWithAnnotation.get(0);
    }

    public static <T> SearchRequest getSearchRequest(QueryBuilder queryBuilder, Class<T> clz) {
        Pair<String, String> check = ElasticUtil.check(queryBuilder, clz);
        SearchRequest searchRequest = new SearchRequest(check.getKey());
        searchRequest.searchType(check.getValue());
        searchRequest.source().query(queryBuilder);
        return searchRequest;
    }
}
