package com.framework.cloud.mybatis.properties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * mybatis 租户配置
 *
 * @author wusiwei
 */
@Data
@ConfigurationProperties(prefix = "framework.mybatis.tenant")
public class TenantProperties {

    @ApiModelProperty(value = "是否开启租户拦截")
    private Boolean isOpen = true;

    @ApiModelProperty(value = "排除表名")
    private List<String> ignoreTableNames = new ArrayList<>();
}
