package com.framework.cloud.tree.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.framework.cloud.tree.TreeFeature;
import com.framework.cloud.tree.binary.BinaryTree;
import com.framework.cloud.tree.utils.TreeUtil;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * 左右节点树 实现
 *
 * @author wusiwei
 */
public class TreeServiceImpl<T extends BinaryTree<T, D, L, N>, D, L, N> implements TreeFeature<T, D, L> {

    @Override
    public List<T> childTree(List<T> list) {
        //层级分组
        TreeMap<L, List<T>> groupTree = list.stream()
                .filter(row -> Objects.nonNull(row.getLevel()))
                .collect(Collectors.groupingBy(T::getLevel, TreeMap::new, Collectors.toList()));
        if (CollectionUtil.isEmpty(groupTree)) {
            return Lists.newArrayList();
        }
        //顶层 键值
        List<T> topTrees = groupTree.get(groupTree.firstKey());
        //遍历递归
        for (T tree : topTrees) {
            TreeUtil.childTree(tree, tree.getTraceId(), list);
        }
        return topTrees;
    }

}
