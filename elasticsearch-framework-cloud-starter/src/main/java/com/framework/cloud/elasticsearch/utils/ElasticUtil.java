package com.framework.cloud.elasticsearch.utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Pair;
import cn.hutool.core.util.ObjectUtil;
import com.framework.cloud.elasticsearch.annotation.ElasticDeclare;
import com.framework.cloud.elasticsearch.annotation.ElasticId;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author wusiwei
 */
public class ElasticUtil {

    /**
     * 获取topic和type
     */
    public static Pair<String, String> getElasticDeclare(Class<?> clz) {
        ElasticDeclare elasticDeclare = clz.getAnnotation(ElasticDeclare.class);
        if (ObjectUtil.isNull(elasticDeclare) || StringUtils.isBlank(elasticDeclare.index())) {
            return null;
        }
        return Pair.of(elasticDeclare.index(), clz.getSimpleName());
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

}
