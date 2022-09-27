package com.framework.cloud.datasource;

import com.framework.cloud.datasource.configuration.DruidConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author wusiwei
 */
@Import(DruidConfiguration.class)
@EnableTransactionManagement
public class DataSourceAutoConfiguration {
}
