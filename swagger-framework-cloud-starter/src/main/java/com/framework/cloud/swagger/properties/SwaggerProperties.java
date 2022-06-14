package com.framework.cloud.swagger.properties;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * swagger配置文件
 *
 * @author wusiwei
 */
@Data
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {

    @ApiModelProperty(value = "扫描包（未填写扫描注解）")
    private String basePackage;

    @ApiModelProperty(value = "页面标题")
    private String title;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "版本号")
    private String version;

    @ApiModelProperty(value = "许可")
    private String license;

    @ApiModelProperty(value = "许可链接")
    private String licenseUrl;

    @ApiModelProperty(value = "分组名称")
    private String groupName;

    @ApiModelProperty(value = "排除路径")
    private List<String> excludePath;
}
