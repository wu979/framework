package com.framework.cloud.swagger;

import com.framework.cloud.swagger.properties.SwaggerProperties;
import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;

/**
 * Swagger配置
 *
 * @author wusiwei
 */
@EnableSwagger2
@EnableSwaggerBootstrapUI
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerConfiguration {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Collections.singletonList(securitySchemes()))
                .securityContexts(Collections.singletonList(securityContexts()))
                ;
    }

    /**
     * 认证方式使用密码模式
     */
    private SecurityScheme securitySchemes() {

        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant("/oauth/token");

        return new OAuthBuilder()
                .name("Authorization")
                .grantTypes(Collections.singletonList(grantType))
                .scopes(Arrays.asList(scopes()))
                .build();
    }

    /**
     * 设置 swagger2 认证的安全上下文
     */
    private SecurityContext securityContexts() {
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(new SecurityReference("Authorization", scopes())))
                .forPaths(PathSelectors.any())
                .build();
    }

    /**
     * 允许认证的scope
     */
    private AuthorizationScope[] scopes() {
        AuthorizationScope authorizationScope = new AuthorizationScope("test", "接口测试");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return authorizationScopes;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("Swagger API")
                //描述
                .description("Swagger Test")
                //创建人
                .contact(new Contact("979", "", "18683789594@163.com"))
                //版本号
                .version("2.0")
                //构建
                .build();
    }

}
