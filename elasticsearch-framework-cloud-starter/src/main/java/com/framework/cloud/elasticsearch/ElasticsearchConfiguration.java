package com.framework.cloud.elasticsearch;

import com.framework.cloud.elasticsearch.callback.ClientCallback;
import com.framework.cloud.elasticsearch.callback.RequestCallback;
import com.framework.cloud.elasticsearch.properties.ElasticsearchProperties;
import com.framework.cloud.elasticsearch.proxy.Elastic;
import com.framework.cloud.elasticsearch.proxy.ElasticTemplate;
import com.framework.cloud.elasticsearch.utils.HttpHostUtil;
import lombok.AllArgsConstructor;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.convert.MappingElasticsearchConverter;
import org.springframework.data.elasticsearch.core.mapping.SimpleElasticsearchMappingContext;

/**
 * elasticsearch configuration initializing
 *
 * @author wusiwei
 */
@AllArgsConstructor
@EnableConfigurationProperties(ElasticsearchProperties.class)
public class ElasticsearchConfiguration {

    private final ElasticsearchProperties elasticsearchProperties;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        HttpHost[] httpHostList = HttpHostUtil.httpHost(elasticsearchProperties);
        RestClientBuilder builder = RestClient.builder(httpHostList);
        //长连接，需要在header中设置Keep-Alive的timeout时间
        builder.setDefaultHeaders(new Header[]{new BasicHeader("Keep-Alive", "timeout=1800, max=1000")});
        //连接延时配置
        builder.setRequestConfigCallback(new RequestCallback(elasticsearchProperties));
        //连接数配置
        builder.setHttpClientConfigCallback(new ClientCallback(elasticsearchProperties));
        return new RestHighLevelClient(builder);
    }

    @Bean
    public ElasticsearchRestTemplate elasticsearchRestTemplate(RestHighLevelClient restHighLevelClient) {
        return new ElasticsearchRestTemplate(restHighLevelClient, new MappingElasticsearchConverter(new SimpleElasticsearchMappingContext()));
    }

    @Bean
    public Elastic elastic(RestHighLevelClient restHighLevelClient, ElasticsearchRestTemplate elasticsearchRestTemplate) {
        return new ElasticTemplate(restHighLevelClient, elasticsearchRestTemplate);
    }
}
