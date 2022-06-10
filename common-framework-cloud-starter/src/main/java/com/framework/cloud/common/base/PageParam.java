package com.framework.cloud.common.base;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * 分页条件构造
 *
 * @author wusiwei
 */
public class PageParam {

    /**
     * 构建分页对象 （无连表查询）
     *
     * @param source 参数
     * @return 分页泛型对象
     */
    public static <T, M> Page<T> buildOrder(M source) {
        BasePage basePage = buildBase(source);
        Page<T> page = buildPage(basePage);
        page.setOrders(buildOrder(null, basePage.getOrders()));
        return page;
    }

    /**
     * 构建分页对象（连表查询）
     * 注：sql有连表并且有重复字段 类似 create_time 需要指定前缀
     *
     * @param prefix 关联查询 有相同字段 如 create_time 排序前缀需要加入指定表前缀
     * @param source 参数
     * @return 分页泛型对象
     */
    public static <T, M> Page<T> buildOrder(String prefix, M source) {
        BasePage basePage = buildBase(source);
        Page<T> page = buildPage(basePage);
        page.setOrders(buildOrder(prefix, basePage.getOrders()));
        return page;
    }


    /**
     * 构建排序
     *
     * @param orderItems 排序规则
     */
    private static List<OrderItem> buildOrder(String prefix, List<OrderItem> orderItems) {
        orderItems.forEach(orderItem -> {
            String column = orderItem.getColumn();
            column = null != prefix ? prefix + column : column;
            orderItem.setColumn(camelToUnderline(column));
        });
        return orderItems;
    }

    /**
     * 驼峰转下划线
     *
     * @param columnName 字段名
     */
    private static String camelToUnderline(String columnName) {
        if (columnName == null || "".equals(columnName.trim())) {
            return "";
        }
        int len = columnName.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = columnName.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(StringPool.UNDERSCORE);
            }
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }

    /**
     * 构建对象
     */
    private static <M> BasePage buildBase(M source) {
        BasePage basePage;
        if (null == source || BasePage.class != source.getClass().getSuperclass()) {
            basePage = new BasePage();
        } else {
            basePage = (BasePage) source;
        }
        return basePage;
    }

    /**
     * 构建对象
     */
    private static <T, M> Page<T> buildPage(BasePage basePage) {
        Page<T> page = new Page<>();
        page.setCurrent(basePage.getCurrent());
        page.setSize(basePage.getSize());
        return page;
    }
}
