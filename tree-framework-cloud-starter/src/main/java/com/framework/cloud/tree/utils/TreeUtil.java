package com.framework.cloud.tree.utils;

import cn.hutool.core.collection.CollectionUtil;
import com.framework.cloud.tree.Tree;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 树形工具类
 *
 * @author wusiwei
 */
public class TreeUtil {

    private TreeUtil() {}

    public static <T extends Tree<T, D, L>, D, L> void childTree(T tree, D traceId, List<T> list) {
        //判断是否叶子节点
        if (tree.getIsLeafNode()) {
            return;
        }
        //子节点
        List<T> childList = list.stream()
                .filter(row -> tree.getId().equals(row.getParentId()))
                .filter(row -> tree.getDepTraceId().equals(traceId))
                .collect(Collectors.toList());
        if (CollectionUtil.isEmpty(childList)) {
            return;
        }
        for (T child : childList) {
            childTree(child, traceId, list);
        }
        tree.setChildList(childList);
    }
}
