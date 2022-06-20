package com.framework.cloud.common.result;

import com.framework.cloud.common.enums.GlobalMessage;

import java.util.List;

/**
 * restful
 *
 * @author wusiwei
 */
public class R<T> {

    public static Result<Void> success() {
        return new Result<Void>(GlobalMessage.SUCCESS.getCode(), GlobalMessage.SUCCESS.getMsg());
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(GlobalMessage.SUCCESS.getCode(), GlobalMessage.SUCCESS.getMsg(), data);
    }

    public static <T> Result<T> error() {
        return error(GlobalMessage.FAIL.getCode(), GlobalMessage.FAIL.getMsg());
    }

    public static <T> Result<T> error(String msg) {
        return error(GlobalMessage.FAIL.getCode(), msg);
    }

    public static <T> Result<T> error(Integer code, String msg) {
        return new Result<T>(code, msg);
    }

    public static <T> Result<List<T>> list(List<T> list) {
        Result<List<T>> result = new Result<List<T>>();
        result.setCode(GlobalMessage.SUCCESS.getCode());
        result.setMsg(GlobalMessage.SUCCESS.getMsg());
        result.setData(list);
        return result;
    }


}
