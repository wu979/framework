package com.framework.cloud.common.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.framework.cloud.common.enums.GlobalMessage;
import com.framework.cloud.common.exception.BizException;
import com.framework.cloud.common.result.Result;
import lombok.experimental.UtilityClass;

import java.util.Collection;

/**
 * 检查
 *
 * @author wusiwei
 */
@UtilityClass
@SuppressWarnings({"rawtypes"})
public class AssertUtil {

    public void notBlank(String text, Integer code, String msg) throws BizException {
        if (StrUtil.isBlank(text)) {
            throw new BizException(code, msg);
        }
    }

    public void notBlank(String text, String msg) throws BizException {
        if (StrUtil.isBlank(text)) {
            throw new BizException(GlobalMessage.FAIL.getCode(), msg);
        }
    }

    public void notBlank(String text, Result result) throws BizException {
        notBlank(text, result.getCode(), result.getMsg());
    }

    public void isBlank(String text, Integer code, String msg) throws BizException {
        if (StrUtil.isBlank(text)) {
            throw new BizException(code, msg);
        }
    }

    public void isBlank(String text, String msg) throws BizException {
        if (StrUtil.isBlank(text)) {
            throw new BizException(GlobalMessage.FAIL.getCode(), msg);
        }
    }

    public void isBlank(String text, Result result) throws BizException {
        isBlank(text, result.getCode(), result.getMsg());
    }

    public void isNull(Object object, Integer code, String msg) throws BizException {
        if (object == null) {
            throw new BizException(code, msg);
        }
    }

    public void isNull(Object object, String msg) throws BizException {
        if (object == null) {
            throw new BizException(GlobalMessage.FAIL.getCode(), msg);
        }
    }

    public void isNull(Object object, Result result) throws BizException {
        isNull(object, result.getCode(), result.getMsg());
    }

    public void nonNull(Object object, Integer code, String msg) throws BizException {
        if (object != null) {
            throw new BizException(code, msg);
        }
    }

    public void nonNull(Object object, String msg) throws BizException {
        if (object != null) {
            throw new BizException(GlobalMessage.FAIL.getCode(), msg);
        }
    }

    public void nonNull(Object object, Result result) throws BizException {
        nonNull(object, result.getCode(), result.getMsg());
    }

    public void notEmpty(Collection collection, Integer code, String msg) throws BizException {
        if (CollUtil.isEmpty(collection)) {
            throw new BizException(code, msg);
        }
    }

    public void notEmpty(Collection collection, String msg) throws BizException {
        if (CollUtil.isEmpty(collection)) {
            throw new BizException(GlobalMessage.FAIL.getCode(), msg);
        }
    }

    public void notEmpty(Collection collection, Result result) throws BizException {
        notEmpty(collection, result.getCode(), result.getMsg());
    }

    public void isTrue(boolean expression, Integer code, String msg) throws BizException {
        if (!expression) {
            throw new BizException(code, msg);
        }
    }

    public void isTrue(boolean expression, String msg) throws BizException {
        if (!expression) {
            throw new BizException(GlobalMessage.FAIL.getCode(), msg);
        }
    }

    public void isTrue(boolean expression, Result result) throws BizException {
        isTrue(expression, result.getCode(), result.getMsg());
    }

    public void isFalse(boolean expression, Integer code, String msg) throws BizException {
        if (expression) {
            throw new BizException(code, msg);
        }
    }

    public void isFalse(boolean expression, String msg) throws BizException {
        if (expression) {
            throw new BizException(GlobalMessage.FAIL.getCode(), msg);
        }
    }

    public void isFalse(boolean expression, Result result) throws BizException {
        isFalse(expression, result.getCode(), result.getMsg());
    }
}
