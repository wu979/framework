package com.framework.cloud.core;

import com.alibaba.cloud.nacos.ConditionalOnNacosDiscoveryEnabled;
import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.cloud.nacos.discovery.NacosWatch;
import com.framework.cloud.common.utils.DateUtil;
import com.framework.cloud.core.properties.NacosWatchProperties;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.CommonsClientAutoConfiguration;
import org.springframework.cloud.client.discovery.simple.SimpleDiscoveryClientAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * nacos metadata
 *
 * @author wusiwei
 */
@Configuration
@AllArgsConstructor
@ConditionalOnNacosDiscoveryEnabled
@EnableConfigurationProperties(NacosWatchProperties.class)
@AutoConfigureBefore({SimpleDiscoveryClientAutoConfiguration.class, CommonsClientAutoConfiguration.class})
public class NacosClientConfiguration {

    private final NacosWatchProperties nacosWatchProperties;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(value = {"spring.cloud.nacos.discovery.watch.enabled"}, matchIfMissing = true)
    public NacosWatch nacosWatch(NacosServiceManager nacosServiceManager, NacosDiscoveryProperties properties, ObjectProvider<ThreadPoolTaskScheduler> taskScheduler) {
        properties.getMetadata().put("startup.time", DateUtil.getNow());
        properties.getMetadata().put("version", nacosWatchProperties.getVersion());
        properties.getMetadata().put("weight", nacosWatchProperties.getWeight());
        return new NacosWatch(nacosServiceManager, properties, taskScheduler);
    }
}