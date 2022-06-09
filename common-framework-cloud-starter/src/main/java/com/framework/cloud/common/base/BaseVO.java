package com.framework.cloud.common.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;

/**
 * 父级VO
 *
 * @author wusiwei
 */
@ToString
public class BaseVO {

    @ApiModelProperty(value = "创建人id")
    private Long createId;

    @ApiModelProperty(value = "修改人id")
    private Long updateId;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;

    @ApiModelProperty(value = "修改时间")
    private Long updateTime;

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}
