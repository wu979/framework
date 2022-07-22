package com.framework.cloud.elasticsearch.utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.framework.cloud.common.base.BasePage;
import com.framework.cloud.common.exception.ElasticException;
import com.framework.cloud.common.utils.UUIDUtil;
import com.framework.cloud.elasticsearch.annotation.ElasticDeclare;
import com.framework.cloud.elasticsearch.annotation.ElasticId;
import com.framework.cloud.elasticsearch.enums.ElasticMessage;
import lombok.SneakyThrows;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wusiwei
 */
public class ElasticUtil {

    public static <T> String indexName(T source) {
        return elasticDeclare(source.getClass()).indexName();
    }

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

    public static <T extends BasePage> List<SortBuilder<FieldSortBuilder>> sortBuilders(T request) {
        List<OrderItem> orders = request.getOrders();
        List<SortBuilder<FieldSortBuilder>> sortBuilders = new ArrayList<>(orders.size());
        for (OrderItem order : orders) {
            sortBuilders.add(SortBuilders.fieldSort(order.getColumn()).order(sortOrder(order.isAsc())));
        }
        return sortBuilders;
    }

    public static SortOrder sortOrder(boolean isAsc) {
        return isAsc ? SortOrder.ASC : SortOrder.DESC;
    }

    /**
     * 获取标注了 {@link ElasticId }  值
     */
    @SneakyThrows
    public static <T> String getDocumentId(T source) {
        String documentId = UUIDUtil.uuid();
        List<Field> listWithAnnotation = FieldUtils.getFieldsListWithAnnotation(source.getClass(), ElasticId.class);
        if (CollectionUtil.isEmpty(listWithAnnotation)) {
            return documentId;
        }
        Field field = listWithAnnotation.get(0);
        if (field == null) {
            return documentId;
        }
        field.setAccessible(true);
        Object obj = FieldUtils.readField(field, source);
        if (null == obj) {
            return documentId;
        }
        return String.valueOf(obj);
    }

}
