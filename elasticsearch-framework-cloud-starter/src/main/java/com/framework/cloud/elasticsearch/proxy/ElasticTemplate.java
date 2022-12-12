package com.framework.cloud.elasticsearch.proxy;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.framework.cloud.common.base.BasePage;
import com.framework.cloud.common.base.PageVO;
import com.framework.cloud.common.exception.ElasticException;
import com.framework.cloud.common.utils.FastJsonUtil;
import com.framework.cloud.common.utils.StringUtil;
import com.framework.cloud.elasticsearch.annotation.ElasticDeclare;
import com.framework.cloud.elasticsearch.enums.ElasticMessage;
import com.framework.cloud.elasticsearch.utils.ElasticUtil;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

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
        ElasticResponse<Boolean> elasticResponse;
        try {
            boolean exists = elasticsearchRestTemplate.indexOps(IndexCoordinates.of(indexName)).exists();
            elasticResponse = ElasticResponse.success(exists);
        } catch (Exception e) {
            elasticResponse = ElasticResponse.error(e.getMessage());
        }
        return elasticResponse;
    }

    @Override
    public <T> ElasticResponse<Boolean> createIndex(String indexName, Class<T> source) {
        ElasticResponse<Boolean> elasticResponse;
        try {
            ElasticDeclare elasticDeclare = ElasticUtil.elasticDeclare(source);
            short shards = elasticDeclare.shards();
            short replicas = elasticDeclare.replicas();
            String settingPath = elasticDeclare.settingPath();
            Settings settings;
            if (StringUtil.isNotBlank(settingPath)) {
                InputStream in = ElasticTemplate.class.getClassLoader().getResourceAsStream(settingPath);
                settings = Settings.builder().loadFromStream(settingPath, in, true).build();
            } else {
                settings = Settings.builder().put("index.number_of_shards", shards).put("index.number_of_replicas", replicas).build();
            }
            CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
            IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(source);
            Document mapping = indexOperations.createMapping(source);
            createIndexRequest.settings(settings);
            createIndexRequest.mapping(mapping);
            CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            if (createIndexResponse != null) {
                elasticResponse = ElasticResponse.success(createIndexResponse.isAcknowledged());
            } else {
                elasticResponse = ElasticResponse.error(ElasticMessage.CREATE_INDEX_ERROR.getMsg());
            }
        } catch (ElasticException e) {
            elasticResponse = ElasticResponse.error(e.getMsg());
        } catch (Exception e) {
            elasticResponse = ElasticResponse.error(e.getMessage());
        }
        return elasticResponse;
    }

    @Override
    public <T> ElasticResponse<Boolean> deleteIndex(String indexName) {
        ElasticResponse<Boolean> elasticResponse;
        try {
            boolean delete = elasticsearchRestTemplate.indexOps(IndexCoordinates.of(indexName)).delete();
            elasticResponse = ElasticResponse.success(delete);
        } catch (Exception e) {
            elasticResponse = ElasticResponse.error(e.getMessage());
        }
        return elasticResponse;
    }

    @Override
    public ElasticResponse<Boolean> deleteById(String indexName, String id) {
        ElasticResponse<Boolean> elasticResponse;
        try {
            DeleteRequest request = new DeleteRequest(indexName).id(id);
            DeleteResponse response = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
            elasticResponse = ElasticResponse.success(RestStatus.OK.equals(response.status()));
        } catch (Exception e) {
            elasticResponse = ElasticResponse.error(e.getMessage());
        }
        return elasticResponse;
    }

    @Override
    public ElasticResponse<Boolean> deleteById(String indexName, List<String> ids) {
        ElasticResponse<Boolean> elasticResponse;
        try {
            BulkRequest request = new BulkRequest();
            for (String id : ids) {
                request.add(new DeleteRequest(indexName).id(id));
            }
            BulkResponse response = restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
            if (response.hasFailures()) {
                elasticResponse = ElasticResponse.error(response.buildFailureMessage());
            } else {
                elasticResponse = ElasticResponse.success(RestStatus.OK.equals(response.status()));
            }
        } catch (Exception e) {
            elasticResponse = ElasticResponse.error(e.getMessage());
        }
        return elasticResponse;
    }

    @Override
    public <T> ElasticResponse<List<String>> deleteQuery(String indexName, QueryBuilder query) {
        ElasticResponse<List<String>> elasticResponse;
        try {
            DeleteByQueryRequest request = new DeleteByQueryRequest(indexName).setQuery(query);
            BulkByScrollResponse response = restHighLevelClient.deleteByQuery(request, RequestOptions.DEFAULT);
            List<BulkItemResponse.Failure> bulkFailures = response.getBulkFailures();
            List<String> ids = null;
            if (CollectionUtil.isNotEmpty(bulkFailures)) {
                ids = bulkFailures.stream().map(BulkItemResponse.Failure::getId).collect(Collectors.toList());
            }
            elasticResponse = ElasticResponse.success(ids);
        } catch (Exception e) {
            elasticResponse = ElasticResponse.error(e.getMessage());
        }
        return elasticResponse;
    }

    @Override
    public <T> ElasticResponse<Boolean> save(String indexName, T source) {
        ElasticResponse<Boolean> elasticResponse;
        try {
            if (ObjectUtil.isNull(source)) {
                return ElasticResponse.error(ElasticMessage.SOURCE_NULL.getMsg());
            }
            String id = ElasticUtil.getDocumentId(source);
            IndexRequest indexRequest = new IndexRequest(indexName);
            indexRequest.id(id);
            indexRequest.source(FastJsonUtil.toJSONString(source), XContentType.JSON);
            IndexResponse response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            if (null != response && null != response.getShardInfo()) {
                elasticResponse = ElasticResponse.success(response.getShardInfo().getSuccessful() > 0);
            } else {
                elasticResponse = ElasticResponse.error(ElasticMessage.SAVE_SOURCE_ERROR.getMsg());
            }
        } catch (ElasticException e) {
            elasticResponse = ElasticResponse.error(e.getMsg());
        } catch (Exception e) {
            elasticResponse = ElasticResponse.error(e.getMessage());
        }
        return elasticResponse;
    }

    @Override
    public <T> ElasticResponse<Boolean> save(String indexName, List<T> sourceList) {
        ElasticResponse<Boolean> elasticResponse;
        try {
            if (CollectionUtil.isEmpty(sourceList)) {
                return ElasticResponse.error(ElasticMessage.SOURCE_NULL.getMsg());
            }
            BulkRequest request = new BulkRequest();
            for (T source : sourceList) {
                String id = ElasticUtil.getDocumentId(source);
                IndexRequest indexRequest = new IndexRequest(indexName);
                indexRequest.id(id);
                indexRequest.source(FastJsonUtil.toJSONString(source), XContentType.JSON);
                request.add(indexRequest);
            }
            BulkResponse response = restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
            if (response.hasFailures()) {
                elasticResponse = ElasticResponse.error(response.buildFailureMessage());
            } else {
                elasticResponse = ElasticResponse.success(RestStatus.OK.equals(response.status()));
            }
        } catch (ElasticException e) {
            elasticResponse = ElasticResponse.error(e.getMsg());
        } catch (Exception e) {
            elasticResponse = ElasticResponse.error(e.getMessage());
        }
        return elasticResponse;
    }

    @Override
    public <T> ElasticResponse<Boolean> update(String indexName, T source) {
        ElasticResponse<Boolean> elasticResponse;
        try {
            if (ObjectUtil.isNull(source)) {
                return ElasticResponse.error(ElasticMessage.SOURCE_NULL.getMsg());
            }
            String id = ElasticUtil.getDocumentId(source);
            UpdateRequest updateRequest = new UpdateRequest(indexName, id);
            updateRequest.doc(FastJsonUtil.toJSONString(source), XContentType.JSON);
            UpdateResponse response = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
            if (null != response && null != response.getShardInfo()) {
                elasticResponse = ElasticResponse.success(response.getShardInfo().getSuccessful() > 0);
            } else {
                elasticResponse = ElasticResponse.error(ElasticMessage.UPDATE_SOURCE_ERROR.getMsg());
            }
        } catch (ElasticException e) {
            elasticResponse = ElasticResponse.error(e.getMsg());
        } catch (Exception e) {
            elasticResponse = ElasticResponse.error(e.getMessage());
        }
        return elasticResponse;
    }

    @Override
    public <T> ElasticResponse<Boolean> update(String indexName, List<T> sourceList) {
        ElasticResponse<Boolean> elasticResponse;
        try {
            if (CollectionUtil.isEmpty(sourceList)) {
                return ElasticResponse.error(ElasticMessage.SOURCE_NULL.getMsg());
            }
            BulkRequest request = new BulkRequest();
            for (T source : sourceList) {
                String id = ElasticUtil.getDocumentId(source);
                UpdateRequest updateRequest = new UpdateRequest(indexName, id);
                updateRequest.doc(FastJsonUtil.toJSONString(source), XContentType.JSON);
                request.add(updateRequest);
            }
            BulkResponse response = restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
            if (response.hasFailures()) {
                elasticResponse = ElasticResponse.error(response.buildFailureMessage());
            } else {
                elasticResponse = ElasticResponse.success(RestStatus.OK.equals(response.status()));
            }
        } catch (ElasticException e) {
            elasticResponse = ElasticResponse.error(e.getMsg());
        } catch (Exception e) {
            elasticResponse = ElasticResponse.error(e.getMessage());
        }
        return elasticResponse;
    }

    @Override
    public <T> ElasticResponse<Boolean> update(String indexName, UpdateQuery updateQuery) {
        ElasticResponse<Boolean> elasticResponse;
        try {
            if (ObjectUtil.isNull(updateQuery)) {
                return ElasticResponse.error(ElasticMessage.SOURCE_NULL.getMsg());
            }
            org.springframework.data.elasticsearch.core.query.UpdateResponse response = elasticsearchRestTemplate.update(updateQuery, IndexCoordinates.of(indexName));
            if (ObjectUtil.isNotNull(response)) {
                elasticResponse = ElasticResponse.success(org.springframework.data.elasticsearch.core.query.UpdateResponse.Result.UPDATED.equals(response.getResult()));
            } else {
                elasticResponse = ElasticResponse.error(ElasticMessage.UPDATE_SOURCE_ERROR.getMsg());
            }
        } catch (ElasticException e) {
            elasticResponse = ElasticResponse.error(e.getMsg());
        } catch (Exception e) {
            elasticResponse = ElasticResponse.error(e.getMessage());
        }
        return elasticResponse;
    }

    @Override
    public <T> ElasticResponse<Boolean> upsert(@NonNull String indexName, @NonNull T source) {
        ElasticResponse<Boolean> elasticResponse;
        try {
            if (ObjectUtil.isNull(source)) {
                return ElasticResponse.error(ElasticMessage.SOURCE_NULL.getMsg());
            }
            String id = ElasticUtil.getDocumentId(source);
            UpdateRequest updateRequest = new UpdateRequest(indexName, id);
            updateRequest.upsert(XContentType.JSON, FastJsonUtil.toJSONString(source));
            UpdateResponse response = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
            if (null != response && null != response.getShardInfo()) {
                elasticResponse = ElasticResponse.success(response.getShardInfo().getSuccessful() > 0);
            } else {
                elasticResponse = ElasticResponse.error(ElasticMessage.UPDATE_SOURCE_ERROR.getMsg());
            }
        } catch (ElasticException e) {
            elasticResponse = ElasticResponse.error(e.getMsg());
        } catch (Exception e) {
            elasticResponse = ElasticResponse.error(e.getMessage());
        }
        return elasticResponse;
    }

    @Override
    public <T> ElasticResponse<Boolean> upsert(@NonNull String indexName, @NonNull List<T> sourceList) {
        ElasticResponse<Boolean> elasticResponse;
        try {
            if (CollectionUtil.isEmpty(sourceList)) {
                return ElasticResponse.error(ElasticMessage.SOURCE_NULL.getMsg());
            }
            BulkRequest request = new BulkRequest();
            for (T source : sourceList) {
                String id = ElasticUtil.getDocumentId(source);
                UpdateRequest updateRequest = new UpdateRequest(indexName, id);
                updateRequest.upsert(XContentType.JSON, FastJsonUtil.toJSONString(source));
                request.add(updateRequest);
            }
            BulkResponse response = restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
            if (response.hasFailures()) {
                elasticResponse = ElasticResponse.error(response.buildFailureMessage());
            } else {
                elasticResponse = ElasticResponse.success(RestStatus.OK.equals(response.status()));
            }
        } catch (ElasticException e) {
            elasticResponse = ElasticResponse.error(e.getMsg());
        } catch (Exception e) {
            elasticResponse = ElasticResponse.error(e.getMessage());
        }
        return elasticResponse;
    }

    @Override
    public <T extends BasePage, R, S extends SortBuilder<S>> PageVO<R> page(String indexName, QueryBuilder queryBuilder, T request, Class<R> source, List<SortBuilder<S>> sortBuilders) {
        PageVO<R> page = new PageVO<R>(request.getCurrent(), request.getSize());
        try {
            Query query = new NativeSearchQueryBuilder()
                    .withQuery(queryBuilder)
                    .withPageable(PageRequest.of(request.getCurrent().intValue() - 1, request.getSize().intValue()))
                    .withSorts(sortBuilders.toArray(new SortBuilder[0]))
                    .build();
            SearchHits<R> search = elasticsearchRestTemplate.search(query, source, IndexCoordinates.of(indexName));
            if (search.hasSearchHits()) {
                List<R> list = search.getSearchHits().stream().map(org.springframework.data.elasticsearch.core.SearchHit::getContent).collect(Collectors.toList());
                page = new PageVO<>(request.getCurrent(), request.getSize(), search.getTotalHits(), list);
            }
        } catch (Exception e) {
            log.error("分页查询失败，索引名称：{}，错误：{}", indexName, e);
        }
        return page;
    }

    @Override
    public ElasticResponse<Aggregations> aggregation(@NonNull String indexName, QueryBuilder queryBuilder) {
        ElasticResponse<Aggregations> elasticResponse;
        try {
            SearchRequest searchRequest = new SearchRequest(indexName);
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.size(0);
            sourceBuilder.from(0);
            sourceBuilder.query(queryBuilder);
            searchRequest.source(sourceBuilder);
            searchRequest.searchType(SearchType.QUERY_THEN_FETCH);
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            if (ObjectUtil.isNotNull(searchResponse.getAggregations())) {
                elasticResponse = ElasticResponse.success(searchResponse.getAggregations());
            } else {
                elasticResponse = ElasticResponse.error(ElasticMessage.AGGREGATIONS_ERROR.getMsg());
            }
        } catch (Exception e) {
            elasticResponse = ElasticResponse.error(e.getMessage());
        }
        return elasticResponse;
    }

    @Override
    public <T> ElasticResponse<T> getById(String indexName, String id, Class<T> source) {
        ElasticResponse<T> elasticResponse;
        try {
            GetRequest request = new GetRequest(indexName).id(id);
            GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
            if (response.isExists() && !response.isSourceEmpty()) {
                elasticResponse = ElasticResponse.success(FastJsonUtil.toJavaObject(response.getSourceAsString(), FastJsonUtil.makeJavaType(source)));
            } else {
                elasticResponse = ElasticResponse.error(ElasticMessage.DOCUMENT_NULL.getMsg());
            }
        } catch (Exception e) {
            elasticResponse = ElasticResponse.error(e.getMessage());
        }
        return elasticResponse;
    }

    @Override
    public <T> ElasticResponse<List<T>> queryList(String indexName, QueryBuilder queryBuilder, Class<T> source) {
        ElasticResponse<List<T>> elasticResponse = new ElasticResponse<>();
        try {
            SearchRequest searchRequest = new SearchRequest(indexName);
            searchRequest.searchType(SearchType.QUERY_THEN_FETCH);
            searchRequest.source().query(queryBuilder);
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = searchResponse.getHits().getHits();
            List<T> result = Lists.newArrayListWithCapacity(hits.length);
            for (SearchHit hit : hits) {
                result.add(FastJsonUtil.toJavaObject(hit.getSourceAsString(), FastJsonUtil.makeJavaType(source)));
            }
            elasticResponse = elasticResponse.setData(result);
        } catch (Exception e) {
            elasticResponse = ElasticResponse.error(e.getMessage());
        }
        return elasticResponse;
    }

    @Override
    public ElasticResponse<Long> getTotalCount(String indexName, QueryBuilder queryBuilder) {
        ElasticResponse<Long> elasticResponse;
        try {
            SearchSourceBuilder builder = new SearchSourceBuilder();
            builder.query(queryBuilder).size(0);
            SearchRequest searchRequest = new SearchRequest(indexName);
            searchRequest.source(builder).searchType(SearchType.QUERY_THEN_FETCH);
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            if (null != searchResponse.getHits() && null != searchResponse.getHits().getTotalHits()) {
                elasticResponse = ElasticResponse.success(searchResponse.getHits().getTotalHits().value);
            } else {
                elasticResponse = ElasticResponse.error(ElasticMessage.SEARCH_TOTAL_ERROR.getMsg());
            }
        } catch (Exception e) {
            elasticResponse = ElasticResponse.error(e.getMessage());
        }
        return elasticResponse;
    }

}
