package com.framework.cloud.mybatis.interceptor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wusiwei
 */
public class JoinQueryWrapper<T> extends QueryWrapper<T> {
    private static final long serialVersionUID = 6957041170223734646L;

    /**
     * 关联查询构造器
     */
    private final List<JoinBuilder> joinBuilder = new ArrayList<>();


    /**
     * 获取 columnName
     */
    @Override
    protected String columnToString(String column) {
        return StringUtils.camelToUnderline(column);
    }


    public static <T> JoinQueryWrapper<T> query() {
        return new JoinQueryWrapper<T>();
    }


    /**
     * 关联查询构造
     */
    public JoinQueryWrapper<T> addJoin(JoinBuilder builder) {
        this.joinBuilder.add(builder);
        return this;
    }

    public List<JoinBuilder> getJoinBuilder() {
        return joinBuilder;
    }

    /**
     * 排序
     */
    public QueryWrapper<T> sort(List<OrderItem> sorts) {
        if (!CollectionUtils.isEmpty(sorts)) {
            sorts.forEach(item -> {
                orderBy(item.getColumn() != null, item.isAsc(), item.getColumn());
            });
        }
        return this;
    }
}
