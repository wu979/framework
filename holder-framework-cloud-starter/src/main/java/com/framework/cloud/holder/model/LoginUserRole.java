package com.framework.cloud.holder.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * 认证用户权限
 *
 * @author wusiwei
 */
@Data
public final class LoginUserRole implements Serializable {
    private static final long serialVersionUID = 4055794698806944561L;

    @ApiModelProperty(value = "用户主键")
    private Long userId;

    @ApiModelProperty(value = "角色列表")
    private Set<String> roleList;

}
