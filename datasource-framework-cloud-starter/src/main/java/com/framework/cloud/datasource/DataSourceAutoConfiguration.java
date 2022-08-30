package com.framework.cloud.datasource;

import com.framework.cloud.datasource.configuration.DruidConfiguration;
import org.springframework.context.annotation.Import;

/**
 * @author wusiwei
 */
@Import(DruidConfiguration.class)
public class DataSourceAutoConfiguration {
}
