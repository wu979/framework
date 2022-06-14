package com.framework.cloud.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.framework.cloud.mybatis.annotation.MapperScanner;
import com.framework.cloud.mybatis.hander.MybatisMetaObjectHandler;
import com.framework.cloud.mybatis.hander.MybatisTenantLineHandler;
import com.framework.cloud.mybatis.interceptor.JoinQueryInterceptor;
import com.framework.cloud.mybatis.interceptor.SqlInterceptor;
import com.framework.cloud.mybatis.primary.IdGenerator;
import com.framework.cloud.mybatis.properties.TenantProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * Mybatis 配置
 *
 * @author wusiwei
 */
@AllArgsConstructor
@EnableConfigurationProperties(TenantProperties.class)
@MapperScanner(basePackages = {"${mybatis-plus.mapperScanner}"}, sqlSessionTemplateRef = "sqlSessionTemplate")
public class MyBatisConfiguration {

    private final TenantProperties tenantProperties;

    /**
     * plus配置
     */
    @Bean
    @ConfigurationProperties(prefix = "mybatis-plus.global-config")
    public GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setMetaObjectHandler(new MybatisMetaObjectHandler());
        globalConfig.setIdentifierGenerator(new IdGenerator());
        return globalConfig;
    }

    /**
     * plus配置
     */
    @Bean
    @ConfigurationProperties(prefix = "mybatis-plus.configuration")
    public MybatisConfiguration mybatisConfiguration() {
        return new MybatisConfiguration();
    }

    /**
     * 插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        paginationInnerInterceptor.setDbType(DbType.MYSQL);
        paginationInnerInterceptor.setMaxLimit(1000L);
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //分页插件拦截器
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        //乐观锁拦截器
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        //防止全表更新与删除拦截器
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        //租户拦截器
        if (tenantProperties.getIsOpen()) {
            interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new MybatisTenantLineHandler(tenantProperties)));
        }
        //自定义拦截器（全SQL）
        interceptor.addInnerInterceptor(new SqlInterceptor());
        //自定义拦截器（join）
        interceptor.addInnerInterceptor(new JoinQueryInterceptor());
        return interceptor;
    }

}
