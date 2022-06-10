package com.framework.cloud.datasource.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Druid 配置文件
 *
 * @author wusiwei
 */
@Data
@Component("druidProperties")
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class DruidProperties {

    private String resetEnable;

    private StatViewServletProperties statViewServlet;

    private WebStatFilterProperties webStatFilter;

    @Data
    public static class StatViewServletProperties {

        private String allow;

        private String deny;

        private String loginUsername;

        private String loginPassword;

    }

    @Data
    public static class WebStatFilterProperties {

        private String urlPattern;

        private String exclusions;
    }

}
