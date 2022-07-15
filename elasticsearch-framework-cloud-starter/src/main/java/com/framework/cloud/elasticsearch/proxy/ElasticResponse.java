package com.framework.cloud.elasticsearch.proxy;

import com.framework.cloud.common.enums.GlobalMessage;
import com.framework.cloud.elasticsearch.enums.ElasticMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author wusiwei
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
public class ElasticResponse<T> {

    private int code;

    private String msg;

    private T data;

    public ElasticResponse() {
        this.code = GlobalMessage.FAIL.getCode();
        this.msg = ElasticMessage.GET_DATA_ERROR.getMsg();
    }

    public ElasticResponse(String msg) {
        this.code = GlobalMessage.FAIL.getCode();
        this.msg = msg;
    }

    public ElasticResponse(T data) {
        this.code = GlobalMessage.FAIL.getCode();
        this.msg = ElasticMessage.GET_DATA_ERROR.getMsg();
        this.data = data;
    }

    public ElasticResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ElasticResponse<Void> success() {
        return new ElasticResponse<Void>(GlobalMessage.SUCCESS.getCode(), GlobalMessage.SUCCESS.getMsg());
    }

    public static <T> ElasticResponse<T> success(T data) {
        return new ElasticResponse<T>(GlobalMessage.SUCCESS.getCode(), GlobalMessage.SUCCESS.getMsg(), data);
    }

    public static <T> ElasticResponse<T> error(String msg) {
        return new ElasticResponse<T>().setCode(GlobalMessage.FAIL.getCode()).setMsg(msg);
    }

    public boolean ok() {
        return GlobalMessage.SUCCESS.getCode().equals(this.code);
    }
}
