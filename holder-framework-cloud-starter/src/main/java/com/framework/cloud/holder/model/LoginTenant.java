package com.framework.cloud.holder.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 认证用户租户模型
 *
 * @author wusiwei
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class LoginTenant implements Serializable {
    private static final long serialVersionUID = 6840219118337638121L;

    @ApiModelProperty(value = "租户主键")
    private Long id;

    @ApiModelProperty(value = "租户标识")
    private String tenantCode;
}
