package com.framework.cloud.holder.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 认证用户模型
 *
 * @author wusiwei
 */
@Data
public final class LoginUser implements Serializable {
    private static final long serialVersionUID = -3438004656749326524L;

    @ApiModelProperty(value = "用户主键")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "租户id")
    private Long tenantId;

    @ApiModelProperty(value = "是否超级管理员")
    private Boolean isAdmin;

}
