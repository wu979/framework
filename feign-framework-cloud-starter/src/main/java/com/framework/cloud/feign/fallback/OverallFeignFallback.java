package com.framework.cloud.feign.fallback;

import com.framework.cloud.common.enums.GlobalMessage;
import com.framework.cloud.common.result.Result;
import com.framework.cloud.feign.enums.FeignMessage;
import feign.RetryableException;
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
        return new Result<>(GlobalMessage.ERROR.getCode(), getMsg());
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
        String msg = cause.getMessage();
        if (cause instanceof RetryableException) {
            RetryableException exception = (RetryableException) cause;
            int status = exception.status();
            msg = FeignMessage.getMsg(status);
            msg = MessageFormat.format(msg, targetName);
        } else {
            if (StringUtils.isBlank(msg)) {
                Throwable cause = this.cause.getCause();
                msg = StringUtils.isBlank(cause.getMessage()) ? GlobalMessage.ERROR.getMsg(): cause.getMessage();
            }
        }
        return msg;
    }
}
