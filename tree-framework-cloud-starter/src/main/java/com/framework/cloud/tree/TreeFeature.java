package com.framework.cloud.tree;

import java.util.List;

/**
 * 左右节点树 接口
 *
 * @author wusiwei
 */
public interface TreeFeature<T extends Tree<T, D, L>, D, L> {

    /**
     * 递归列表
     *
     * @param list 所有数据
     * @return 递归树
     * @apiNote level 数据层级不能为空,根据层级递归
     */
    List<T> childTree(List<T> list);

}
