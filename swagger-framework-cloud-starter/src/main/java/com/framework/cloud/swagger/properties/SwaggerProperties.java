package com.framework.cloud.swagger.properties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * swagger配置文件
 *
 * @author wusiwei
 */
@Data
@ConfigurationProperties(prefix = "framework.knife4j")
public class SwaggerProperties {

    @ApiModelProperty(value = "作者信息")
    private Contact contact;

    @ApiModelProperty(value = "扫描路径")
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

    @ApiModelProperty(value = "分组")
    private String groupName;

    @Data
    @ApiModel(value = "作者信息")
    public static class Contact {

        @ApiModelProperty(value = "名称")
        private String name;

        @ApiModelProperty(value = "链接")
        private String url;

        @ApiModelProperty(value = "邮箱")
        private String email;

    }
}
