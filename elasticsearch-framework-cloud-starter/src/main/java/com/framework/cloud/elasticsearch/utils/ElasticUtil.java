package com.framework.cloud.elasticsearch.utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Pair;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ObjectUtil;
import com.framework.cloud.common.exception.ElasticException;
import com.framework.cloud.elasticsearch.annotation.ElasticDeclare;
import com.framework.cloud.elasticsearch.annotation.ElasticId;
import com.framework.cloud.elasticsearch.enums.ElasticMessage;
import lombok.SneakyThrows;
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

    public static <T> String indexName(Class<T> source) {
        return elasticDeclare(source).indexName();
    }

    public static <T> String indexName(ElasticDeclare elasticDeclare) {
        return elasticDeclare.indexName();
    }

    public static <T> ElasticDeclare elasticDeclare(Class<T> source) {
        if (ObjectUtil.isNull(source)) {
            throw new ElasticException(ElasticMessage.SOURCE_NULL.getMsg());
        }
        ElasticDeclare elasticDeclare = source.getAnnotation(ElasticDeclare.class);
        if (ObjectUtil.isNull(elasticDeclare)) {
            throw new ElasticException(ElasticMessage.ELASTIC_DECLARE_NULL.getMsg());
        }
        return elasticDeclare;
    }

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
        //if (null == clz) {
        //    throw new ElasticException(ElasticMessage.CLZ_NULL.getMsg());
        //}
        //if (null == elasticDeclare) {
        //    throw new ElasticException(ElasticMessage.NOT_FOUND_DECLARE.getMsg());
        //}
        //if (StringUtils.isBlank(elasticDeclare.indexName())) {
        //    throw new ElasticException(ElasticMessage.INDEX_ERROR.getMsg());
        //}
        //return Pair.of(elasticDeclare.indexName(), elasticDeclare.indexType());
        return null;
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

    /**
     * 获取标注了 {@link ElasticId }  值
     */
    @SneakyThrows
    public static <T> String getDocumentId(T t) {
        String documentId = UUID.randomUUID().toString().replaceAll("-", "");
        List<Field> listWithAnnotation = FieldUtils.getFieldsListWithAnnotation(t.getClass(), ElasticId.class);
        if (CollectionUtil.isEmpty(listWithAnnotation)) {
            return documentId;
        }
        Field field = listWithAnnotation.get(0);
        if (field == null) {
            return documentId;
        }
        field.setAccessible(true);
        Object obj = FieldUtils.readField(field, t);
        if (null == obj) {
            return documentId;
        }
        return String.valueOf(obj);
    }

    public static <T> SearchRequest getSearchRequest(QueryBuilder queryBuilder, Class<T> clz) {
        Pair<String, String> check = ElasticUtil.check(queryBuilder, clz);
        SearchRequest searchRequest = new SearchRequest(check.getKey());
        searchRequest.searchType(check.getValue());
        searchRequest.source().query(queryBuilder);
        return searchRequest;
    }
}
