package com.framework.cloud.datasource.configuration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.framework.cloud.datasource.properties.DruidProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * Druid 监控配置
 *
 * @author wusiwei
 */
@SuppressWarnings({"rawtypes"})
@ConditionalOnProperty(name = "spring.datasource.druid.enable", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(DruidProperties.class)
public class DruidConfiguration {

    private final DruidProperties druidProperties;

    public DruidConfiguration(DruidProperties druidProperties) {
        this.druidProperties = druidProperties;
    }

    @Bean("startViewServlet")
    public ServletRegistrationBean startViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //Ip白名单,多个在value中用逗号隔开，不配置默认所有
        servletRegistrationBean.addInitParameter("allow", druidProperties.getStatViewServlet().getAllow());
        //ip 黑名单，这里黑名单优先级大于白名单
        servletRegistrationBean.addInitParameter("deny", druidProperties.getStatViewServlet().getDeny());
        //控制台管理
        servletRegistrationBean.addInitParameter("loginUsername", druidProperties.getStatViewServlet().getLoginUsername());
        servletRegistrationBean.addInitParameter("loginPassword", druidProperties.getStatViewServlet().getLoginPassword());
        //是否可以点击页面的重置按钮
        servletRegistrationBean.addInitParameter("resetEnable", druidProperties.getResetEnable());
        return servletRegistrationBean;
    }

    @Bean("statBeanFilter")
    public FilterRegistrationBean statFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则
        filterRegistrationBean.addUrlPatterns(druidProperties.getWebStatFilter().getUrlPattern());
        //忽略过滤的格式
        filterRegistrationBean.addInitParameter("exclusions", druidProperties.getWebStatFilter().getExclusions());
        return filterRegistrationBean;
    }

}
