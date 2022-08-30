package com.framework.cloud.elasticsearch.utils;

import com.framework.cloud.elasticsearch.properties.ElasticsearchProperties;
import org.apache.http.HttpHost;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wusiwei
 */
public class HttpHostUtil {

    public static final String SPLIT = ":";

    private HttpHostUtil() {
    }

    public static HttpHost[] httpHost(ElasticsearchProperties elasticsearchProperties) {
        List<HttpHost> httpHostList = new ArrayList<>();
        String schema = elasticsearchProperties.getSchema();
        List<String> uris = elasticsearchProperties.getUris();
        if (CollectionUtils.isEmpty(uris)) {
            throw new NullPointerException("elasticsearch uris is empty, please check");
        }
        boolean check = uris.stream().allMatch(HttpHostUtil::checkUri);
        if (!check) {
            throw new IllegalArgumentException("please configure uris ip:port correctly");
        }
        for (String uri : uris) {
            String ip = uri.substring(0, uri.indexOf(SPLIT));
            String port = uri.substring(uri.indexOf(SPLIT) + 1);
            httpHostList.add(new HttpHost(ip, Integer.parseInt(port), schema));
        }
        return httpHostList.toArray(new HttpHost[0]);
    }

    private static boolean checkUri(String uri) {
        return uri.contains(SPLIT);
    }
}
