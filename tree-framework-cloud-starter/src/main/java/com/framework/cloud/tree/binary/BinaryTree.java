package com.framework.cloud.tree.binary;

import cn.hutool.core.util.NumberUtil;
import com.framework.cloud.tree.Tree;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 左右节点树
 *
 * @author wusiwei
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class BinaryTree<T extends Serializable, D, L, N> extends Tree<T, D, L> implements Serializable {
    private static final long serialVersionUID = 7007049243238218494L;

    @ApiModelProperty(value = "左节点")
    private N left;

    @ApiModelProperty(value = "右节点")
    private N right;

    @Override
    public Boolean getIsLeafNode() {
        if (this.left instanceof Number) {
            Number right = (Number) this.right;
            Number left = (Number) this.left;
            return NumberUtil.sub(right, 1).equals(left);
        }
        return false;
    }

}
