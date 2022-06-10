package com.framework.cloud.core.tree;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 公共树
 *
 * @author wusiwei
 */
@Data
public class Tree<T extends Serializable, D, L> implements Serializable {
    private static final long serialVersionUID = -2541310174664195663L;

    @ApiModelProperty(value = "主键")
    private D id;

    @ApiModelProperty(value = "层级")
    private L level;

    @ApiModelProperty(value = "父级")
    private D parentId;

    @ApiModelProperty(value = "祖链id")
    private D depTraceId;

    @ApiModelProperty(value = "是否叶子节点")
    private Boolean isLeafNode;

    @ApiModelProperty(value = "子集")
    private List<T> childList = new ArrayList<>();

}
