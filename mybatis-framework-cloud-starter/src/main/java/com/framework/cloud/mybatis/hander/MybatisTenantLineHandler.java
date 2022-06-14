package com.framework.cloud.mybatis.hander;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.framework.cloud.mybatis.properties.TenantProperties;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;

/**
 * 租户拦截器
 *
 * @author wusiwei
 */
public class MybatisTenantLineHandler implements TenantLineHandler {

    private final TenantProperties tenantProperties;

    public MybatisTenantLineHandler(TenantProperties tenantProperties) {
        this.tenantProperties = tenantProperties;
    }

    @Override
    public Expression getTenantId() {
        return new LongValue(1);
    }

    @Override
    public boolean ignoreTable(String tableName) {
        return tenantProperties.getIgnoreTableNames().contains(tableName);
    }

}
