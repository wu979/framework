package com.framework.cloud.elasticsearch.proxy;

import cn.hutool.core.lang.Pair;
import com.framework.cloud.common.exception.ElasticException;
import com.framework.cloud.common.utils.FastJsonUtil;
import com.framework.cloud.elasticsearch.annotation.ElasticDeclare;
import com.framework.cloud.elasticsearch.enums.ElasticMessage;
import com.framework.cloud.elasticsearch.utils.ElasticUtil;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;

import java.util.List;

/**
 * @author wusiwei
 */
@Slf4j
@AllArgsConstructor
@ConditionalOnBean(RestHighLevelClient.class)
public class ElasticTemplate implements Elastic {

    private final RestHighLevelClient restHighLevelClient;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public ElasticResponse<Boolean> isExists(String indexName) {
        ElasticResponse<Boolean> response = new ElasticResponse<>();
        try {
            boolean exists = elasticsearchRestTemplate.indexOps(IndexCoordinates.of(indexName)).exists();
            response = ElasticResponse.success(exists);
        } catch (Exception e) {
            log.error("查询索引失败，索引名称：{}，错误：{}", indexName, e);
        }
        return response;
    }

    @Override
    public <T> ElasticResponse<Boolean> createIndex(String indexName, Class<T> source) {
        ElasticDeclare elasticDeclare = ElasticUtil.elasticDeclare(source);
        short shards = elasticDeclare.shards();
        short replicas = elasticDeclare.replicas();
        ElasticResponse<Boolean> response = new ElasticResponse<>(ElasticMessage.CREATE_INDEX_ERROR.getMsg());
        try {
            CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
            IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(source);
            Document mapping = indexOperations.createMapping(source);
            Settings.Builder put = Settings.builder().put("index.number_of_shards", shards).put("index.number_of_replicas", replicas);
            createIndexRequest.settings(put);
            createIndexRequest.mapping(mapping);
            CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            if (createIndexResponse != null) {
                response = ElasticResponse.success(createIndexResponse.isAcknowledged());
            }
        } catch (Exception e) {
            log.error("创建索引失败，索引名称：{}，错误：{}", indexName, e);
        }
        return response;
    }

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
        if (listElasticResponse.ok()) {
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
    public <T> ElasticResponse<Boolean> save(T source) {
        ElasticResponse<Boolean> elasticResponse = new ElasticResponse<>();
        try {
            if (null == source) {
                return elasticResponse;
            }
            String id = ElasticUtil.getDocumentId(source);
            Pair<String, String> check = ElasticUtil.check(id, source.getClass());
            IndexRequest indexRequest = new IndexRequest(check.getKey());
            indexRequest.id(id);
            indexRequest.source(FastJsonUtil.toJSONString(source), XContentType.JSON);
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
