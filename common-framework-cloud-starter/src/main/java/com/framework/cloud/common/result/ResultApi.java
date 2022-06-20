package com.framework.cloud.common.result;

import com.framework.cloud.common.enums.GlobalMessage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 第三方 restful
 *
 * @author wusiwei
 */
@Data
public class ResultApi<T> implements Serializable {
    private static final long serialVersionUID = 4780349828455373689L;

    @ApiModelProperty(value = "访问是否成功")
    private Boolean call = Boolean.FALSE;

    @ApiModelProperty(value = "状态码")
    private Integer code;

    @ApiModelProperty(value = "消息")
    private String msg;

    @ApiModelProperty(value = "消息体")
    private T data;

    public ResultApi(String msg) {
        this.code = GlobalMessage.FAIL.getCode();
        this.msg = msg;
    }

    public ResultApi(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultApi(Boolean call, Integer code, String msg) {
        this.call = call;
        this.code = code;
        this.msg = msg;
    }

    public boolean success() {
        return GlobalMessage.SUCCESS.getCode().equals(this.code);
    }
}