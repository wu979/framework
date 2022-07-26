package com.framework.cloud.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wusiwei
 */
@Data
@ConfigurationProperties(prefix = "framework.nacos.watch")
public class NacosWatchProperties {

    /**
     * 版本号
     */
    private String version = "v1";

    /**
     * 权重
     */
    private String weight = "1";

}
