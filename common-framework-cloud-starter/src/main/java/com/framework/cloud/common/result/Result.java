package com.framework.cloud.common.result;

import com.framework.cloud.common.enums.GlobalMessage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * restful
 *
 * @author wusiwei
 */
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 5114320052143189787L;

    @ApiModelProperty(value = "状态码")
    private Integer code;

    @ApiModelProperty(value = "消息")
    private String msg;

    @ApiModelProperty(value = "消息体")
    private T data;

    public Result() {
    }

    public Result(String msg) {
        this.code = GlobalMessage.FAIL.getCode();
        this.msg = msg;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public boolean success() {
        return GlobalMessage.SUCCESS.getCode().equals(this.code);
    }
}
