package com.framework.cloud.mybatis.configuration;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.framework.cloud.mybatis.annotation.MapperScanner;
import com.framework.cloud.mybatis.hander.MybatisLocalDateTimeTypeHandler;
import com.framework.cloud.mybatis.hander.MybatisLocalDateTypeHandler;
import lombok.AllArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 *
 * @author wusiwei
 */
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(MybatisPlusProperties.class)
@MapperScanner(basePackages = { "${mybatis-plus.mapperScanner}" }, sqlSessionTemplateRef = "sqlSessionTemplate")
public class MyBatisConfiguration {

    private final DataSource dataSource;

    private final MybatisPlusProperties mybatisPlusProperties;

    /**
     * plus配置
     */
    @Bean
    @ConfigurationProperties(prefix = "mybatis-plus.global-config")
    public GlobalConfig globalConfig() {
        return new GlobalConfig();
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
     * session工厂
     */
    @Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactory() throws Exception {
        GlobalConfig globalConfig = globalConfig();
        MybatisConfiguration mybatisConfiguration = mybatisConfiguration();
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        List<Resource[]> resourceList = new ArrayList<>();
        for (String mapperLocation : mybatisPlusProperties.getMapperLocations()) {
            resourceList.add(new PathMatchingResourcePatternResolver().getResources(mapperLocation));
        }
        // 设置数据源
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 设置XML的映射路径
        sqlSessionFactoryBean.setMapperLocations(resourceList.stream().flatMap(Arrays::stream).collect(Collectors.toList()).toArray(new Resource[resourceList.size()]));
        // 设置实体类扫描路径
        sqlSessionFactoryBean.setTypeAliasesPackage(mybatisPlusProperties.getTypeAliasesPackage());
        // 设置枚举扫描路径
        sqlSessionFactoryBean.setTypeEnumsPackage(mybatisPlusProperties.getTypeEnumsPackage());
        // 设置mybatisplus全局配置
        sqlSessionFactoryBean.setGlobalConfig(globalConfig);
        // 设置mybatis的配置
        sqlSessionFactoryBean.setConfiguration(mybatisConfiguration);
        // 加入拦截器
        sqlSessionFactoryBean.setPlugins(mybatisPlusInterceptor());
        // 加入hander
        sqlSessionFactoryBean.setTypeHandlers(dateTime(), date());
        return sqlSessionFactoryBean;
    }

    /**
     * 分页插件拦截器
     */
    @Bean
    public PaginationInnerInterceptor paginationInterceptor() {
        PaginationInnerInterceptor interceptor = new PaginationInnerInterceptor();
        interceptor.setDbType(DbType.MYSQL);
        interceptor.setMaxLimit(1000L);
        return interceptor;
    }

    /**
     * 乐观锁拦截器
     */
    @Bean
    public OptimisticLockerInnerInterceptor optimisticLockerInnerInterceptor() {
        return new OptimisticLockerInnerInterceptor();
    }

    /**
     * 防止全表更新与删除插件
     */
    @Bean
    public BlockAttackInnerInterceptor blockAttackInnerInterceptor() {
        return new BlockAttackInnerInterceptor();
    }

    /**
     * 插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(paginationInterceptor());
        interceptor.addInnerInterceptor(optimisticLockerInnerInterceptor());
        interceptor.addInnerInterceptor(blockAttackInnerInterceptor());
        return interceptor;
    }

    /**
     * 时间处理
     */
    @Bean
    public MybatisLocalDateTimeTypeHandler dateTime() {
        return new MybatisLocalDateTimeTypeHandler();
    }

    /**
     * 时间处理
     */
    @Bean
    public MybatisLocalDateTypeHandler date() {
        return new MybatisLocalDateTypeHandler();
    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
