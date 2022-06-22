package com.framework.cloud.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.framework.cloud.mybatis.annotation.MapperScanner;
import com.framework.cloud.mybatis.hander.MybatisMetaObjectHandler;
import com.framework.cloud.mybatis.hander.MybatisTenantLineHandler;
import com.framework.cloud.mybatis.properties.TenantProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * Mybatis 配置
 *
 * @author wusiwei
 */
@AllArgsConstructor
@EnableConfigurationProperties(TenantProperties.class)
@MapperScanner(basePackages = {"${mybatisPlus.mapperScanner}"}, sqlSessionTemplateRef = "sqlSessionTemplate")
public class MyBatisConfiguration {

    private final TenantProperties tenantProperties;

    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MybatisMetaObjectHandler();
    }

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
        return interceptor;
    }
}
