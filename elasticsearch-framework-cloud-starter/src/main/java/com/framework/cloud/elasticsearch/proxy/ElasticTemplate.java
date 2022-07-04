package com.framework.cloud.elasticsearch.proxy;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.framework.cloud.common.utils.FastJsonUtil;
import com.framework.cloud.elasticsearch.annotation.ElasticDeclare;
import com.framework.cloud.elasticsearch.utils.ElasticUtil;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

/**
 * @author wusiwei
 */
@Slf4j
@AllArgsConstructor
@ConditionalOnBean(RestHighLevelClient.class)
public class ElasticTemplate implements Elastic {

    private final RestHighLevelClient restHighLevelClient;

    @Override
    public <T> T getById(Long id, Class<T> clz) {
        if (ObjectUtil.isNull(id) || ObjectUtil.isNull(clz)) {
            return null;
        }
        ElasticDeclare elasticDeclare = clz.getAnnotation(ElasticDeclare.class);
        if (ObjectUtil.isNull(elasticDeclare) || StringUtils.isBlank(elasticDeclare.index())) {
            return null;
        }
        GetRequest request = new GetRequest(elasticDeclare.index()).id(String.valueOf(id));
        try {
            GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
            if (response.isExists() || response.isSourceEmpty()) {
                return FastJsonUtil.toJavaObject(response.getSourceAsString(), FastJsonUtil.makeJavaType(clz));
            }
        } catch (Exception e) {
            log.error("");
        }
        return null;
    }

    @Override
    public <T> T queryOne(SearchSourceBuilder sourceBuilder, Class<T> clz) {
        List<T> result = queryList(sourceBuilder, clz);
        if (CollectionUtils.isNotEmpty(result)) {
            return result.get(0);
        }
        return null;
    }

    @Override
    public <T> List<T> queryList(SearchSourceBuilder sourceBuilder, Class<T> clz) {
        ElasticDeclare elasticDeclare = clz.getAnnotation(ElasticDeclare.class);
        if (ObjectUtil.isNull(elasticDeclare) || StringUtils.isBlank(elasticDeclare.index())) {
            return Collections.emptyList();
        }
        Field idField = ElasticUtil.getIdField(clz);
        if (null == idField) {
            return Collections.emptyList();
        }
        SearchRequest searchRequest = new SearchRequest(elasticDeclare.index()).source(sourceBuilder);
        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = searchResponse.getHits().getHits();
            List<T> result = Lists.newArrayListWithCapacity(hits.length);
            for (SearchHit hit : hits) {
                T obj = FastJsonUtil.toJavaObject(hit.getSourceAsString(), FastJsonUtil.makeJavaType(clz));
                Object idObj = FieldUtils.readField(idField, obj, true);
                if (null == idObj) {
                    FieldUtils.writeField(idField, obj, hit.getId(), true);
                }
                result.add(obj);
            }
            return result;
        } catch (Exception e) {
            log.error("");
        }
        return Collections.emptyList();
    }
}
