package com.framework.cloud.feign.fallback;

import com.framework.cloud.common.enums.MessageEnum;
import com.framework.cloud.common.result.Result;
import com.framework.cloud.feign.enums.FeignMessageEnum;
import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Objects;

/**
 * 全局默认Feign降级
 *
 * @author wusiwei
 */
@Slf4j
@AllArgsConstructor
public class OverallFeignFallback<T> implements MethodInterceptor {

    private final Class<T> targetType;
    private final String targetName;
    private final Throwable cause;

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) {
        return new Result<>(MessageEnum.ERROR.getCode(), getMsg());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OverallFeignFallback<?> that = (OverallFeignFallback<?>) obj;
        return targetType.equals(that.targetType);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(targetType);
    }

    private String getMsg() {
        String msg;
        if (cause instanceof FeignException) {
            FeignException exception = (FeignException) cause;
            int status = exception.status();
            msg = FeignMessageEnum.getMsg(status);
            msg = MessageFormat.format(msg, targetName);
        } else {
            msg = StringUtils.isBlank(cause.getMessage()) ? MessageEnum.ERROR.getMsg() : cause.getMessage();
        }
        return msg;
    }
}
