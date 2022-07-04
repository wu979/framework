package com.framework.cloud.elasticsearch.callback;

import com.framework.cloud.elasticsearch.properties.ElasticsearchProperties;
import lombok.AllArgsConstructor;
import org.apache.http.client.config.RequestConfig;
import org.elasticsearch.client.RestClientBuilder;

/**
 * @author wusiwei
 */
@AllArgsConstructor
public class RequestCallback implements RestClientBuilder.RequestConfigCallback {

    private final ElasticsearchProperties elasticsearchProperties;

    @Override
    public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder builder) {
        builder.setConnectTimeout(elasticsearchProperties.getConnectTimeOut());
        builder.setSocketTimeout(elasticsearchProperties.getSocketTimeOut());
        builder.setConnectionRequestTimeout(elasticsearchProperties.getConnectionRequestTimeOut());
        return builder;
    }
}
