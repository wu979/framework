package com.framework.cloud.elasticsearch.proxy;

import cn.hutool.core.lang.Pair;
import com.framework.cloud.common.exception.ElasticException;
import com.framework.cloud.common.utils.FastJsonUtil;
import com.framework.cloud.elasticsearch.enums.ElasticMessage;
import com.framework.cloud.elasticsearch.utils.ElasticUtil;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import java.lang.reflect.Field;
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
    public <T> ElasticResponse<T> getById(String id, Class<T> clz) {
        Pair<String, String> check = ElasticUtil.check(id, clz);
        ElasticResponse<T> elasticResponse = new ElasticResponse<>();
        GetRequest request = new GetRequest(check.getKey()).id(id);
        try {
            GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
            if (response.isExists() && !response.isSourceEmpty()) {
                elasticResponse = ElasticResponse.success(FastJsonUtil.toJavaObject(response.getSourceAsString(), FastJsonUtil.makeJavaType(clz)));
            }
        } catch (Exception e) {
            elasticResponse = ElasticResponse.error(e.getMessage());
        }
        return elasticResponse;
    }

    @Override
    public <T> ElasticResponse<T> queryOne(QueryBuilder queryBuilder, Class<T> clz) {
        ElasticResponse<List<T>> listElasticResponse = queryList(queryBuilder, clz);
        ElasticResponse<T> elasticResponse;
        if (listElasticResponse.success()) {
            elasticResponse = ElasticResponse.success(listElasticResponse.getData().get(0));
        } else {
            elasticResponse = ElasticResponse.error(listElasticResponse.getMsg());
        }
        return elasticResponse;
    }

    @Override
    public <T> ElasticResponse<List<T>> queryList(QueryBuilder queryBuilder, Class<T> clz) {
        return queryList(ElasticUtil.getSearchRequest(queryBuilder, clz), clz);
    }

    @Override
    public <T> ElasticResponse<List<T>> queryList(SearchRequest searchRequest, Class<T> clz) {
        ElasticResponse<List<T>> elasticResponse = new ElasticResponse<>();
        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = searchResponse.getHits().getHits();
            List<T> result = Lists.newArrayListWithCapacity(hits.length);
            for (SearchHit hit : hits) {
                result.add(FastJsonUtil.toJavaObject(hit.getSourceAsString(), FastJsonUtil.makeJavaType(clz)));
            }
            elasticResponse = elasticResponse.setData(result);
        } catch (Exception e) {
            elasticResponse = ElasticResponse.error(e.getMessage());
        }
        return elasticResponse;
    }

    @Override
    public <T> ElasticResponse<Long> getTotalCount(QueryBuilder queryBuilder, Class<T> clz) {
        SearchRequest searchRequest = ElasticUtil.getSearchRequest(queryBuilder, clz);
        ElasticResponse<Long> elasticResponse = new ElasticResponse<>();
        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            if (null != searchResponse.getHits() && null != searchResponse.getHits().getTotalHits()) {
                elasticResponse = ElasticResponse.success(searchResponse.getHits().getTotalHits().value);
            }
        } catch (Exception e) {
            elasticResponse = ElasticResponse.error(e.getMessage());
        }
        return elasticResponse;
    }

    @Override
    public <T> ElasticResponse<Boolean> save(T t) {
        if (null == t) {
            throw new ElasticException(ElasticMessage.DATA_NULL.getMsg());
        }
        Field idField = ElasticUtil.getIdField(t.getClass());
        if (null == idField) {
            throw new ElasticException(ElasticMessage.NOT_FOUND_ELASTIC_ID.getMsg());
        }
        ElasticResponse<Boolean> elasticResponse = new ElasticResponse<>();
        try {
            idField.setAccessible(true);
            String id = String.valueOf(FieldUtils.readField(idField, t));
            if (null == id) {
                throw new ElasticException(ElasticMessage.NOT_FOUND_ID.getMsg());
            }
            Pair<String, String> check = ElasticUtil.check(id, t.getClass());
            IndexRequest indexRequest = new IndexRequest(check.getKey());
            indexRequest.id(id);
            indexRequest.source(FastJsonUtil.toJSONString(t), XContentType.JSON);
            IndexResponse response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            if (null != response && null != response.getShardInfo()) {
                elasticResponse = ElasticResponse.success(response.getShardInfo().getSuccessful() > 0);
            }
        } catch (ElasticException e) {
            elasticResponse = ElasticResponse.error(e.getMsg());
        } catch (Exception e) {
            elasticResponse = ElasticResponse.error(e.getMessage());
        }
        return elasticResponse;
    }
}
