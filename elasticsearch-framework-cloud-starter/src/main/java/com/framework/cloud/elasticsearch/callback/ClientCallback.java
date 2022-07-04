package com.framework.cloud.elasticsearch.callback;

import com.framework.cloud.elasticsearch.properties.ElasticsearchProperties;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClientBuilder;

/**
 * @author wusiwei
 */
@AllArgsConstructor
public class ClientCallback implements RestClientBuilder.HttpClientConfigCallback {

    private final ElasticsearchProperties elasticsearchProperties;

    @Override
    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder builder) {
        builder.setMaxConnTotal(elasticsearchProperties.getMaxConnectNum());
        builder.setMaxConnPerRoute(elasticsearchProperties.getMaxConnectPerRoute());
        String username = elasticsearchProperties.getUsername();
        String password = elasticsearchProperties.getPassword();
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, credentials);
            builder.setDefaultCredentialsProvider(credentialsProvider);
        }
        return builder;
    }
}
