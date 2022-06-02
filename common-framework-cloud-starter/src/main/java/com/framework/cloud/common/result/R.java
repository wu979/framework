package com.framework.cloud.common.result;

import com.framework.cloud.common.enums.MessageEnum;

import java.util.List;

/**
 * restful
 *
 * @author wusiwei
 */
public class R<T> {

    public static Result<Void> success() {
        return new Result<Void>(MessageEnum.SUCCESS.getCode(), MessageEnum.SUCCESS.getMsg());
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(MessageEnum.SUCCESS.getCode(), MessageEnum.SUCCESS.getMsg(), data);
    }

    public static <T> Result<T> error() {
        return error(MessageEnum.FAIL.getCode(), MessageEnum.FAIL.getMsg());
    }

    public static <T> Result<T> error(String msg) {
        return error(MessageEnum.FAIL.getCode(), msg);
    }

    public static <T> Result<T> error(Integer code, String msg) {
        return new Result<T>(code, msg);
    }

    public static <T> Result<List<T>> list(List<T> list) {
        Result<List<T>> result = new Result<List<T>>();
        result.setCode(MessageEnum.SUCCESS.getCode());
        result.setMsg(MessageEnum.SUCCESS.getMsg());
        result.setData(list);
        return result;
    }


}
