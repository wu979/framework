package com.framework.cloud.logging.annotation;

import com.framework.cloud.common.utils.FastJsonUtil;
import com.framework.cloud.common.utils.StringUtil;
import io.swagger.annotations.ApiOperation;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;

import static com.alibaba.fastjson2.JSONWriter.Feature.MapSortField;

/**
 * print log
 *
 * @author wusiwei
 */
@Slf4j
@Aspect
@RequiredArgsConstructor
@SuppressWarnings("all")
@Order(2)
public class LogAspect {

    /**
     * 打印类或方法上的 {@link Log}
     */
    @Around("@within(com.framework.cloud.logging.annotation.Log) || @annotation(com.framework.cloud.logging.annotation.Log)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Method method = methodSignature.getMethod();
        Object response = null;
        long start = System.currentTimeMillis();
        try {
            response = joinPoint.proceed();
        } finally {
            String time = getTime(start);
            Log annotation = Optional.ofNullable(method.getAnnotation(Log.class)).orElse(joinPoint.getTarget().getClass().getAnnotation(Log.class));
            if (Objects.isNull(annotation)) {
                return response;
            }
            String methodType = "Undefined", url = "Undefined";
            if (null != attributes && null != attributes.getRequest()) {
                methodType = attributes.getRequest().getMethod();
                url = attributes.getRequest().getRequestURI();
            }
            Object[] requestArgs = getRequestArgs(annotation.request(), joinPoint.getArgs());
            String note = getNote(annotation.note(), method);
            Print.PrintBuilder print = Print.builder().request(requestArgs);
            if (annotation.response()) {
                print.response(response);
            }
            String data = FastJsonUtil.toJSONString(print.build(), MapSortField);
            log.info("[{}] {}， note：{}， time：{}， data：{}", methodType, url, note, time, data);
        }
        return response;
    }

    private Object[] getRequestArgs(boolean request, Object[] args) {
        Object[] printArgs = new Object[args.length];
        if (!request) {
            return printArgs;
        }
        for (int i = 0; i < args.length; i++) {
            if ((args[i] instanceof HttpServletRequest) || args[i] instanceof HttpServletResponse) {
                continue;
            }
            if (args[i] instanceof byte[]) {
                printArgs[i] = "byte array";
            } else if (args[i] instanceof MultipartFile) {
                printArgs[i] = "file";
            } else {
                printArgs[i] = args[i];
            }
        }
        return printArgs;
    }

    private String getNote(String note, Method method) {
        if (StringUtil.isBlank(note)) {
            ApiOperation operation = method.getAnnotation(ApiOperation.class);
            if (Objects.nonNull(operation)) {
                note = StringUtil.isBlank(operation.value()) ? operation.notes() : operation.value();
            }
        }
        return note;
    }

    private String getTime(long start) {
        return System.currentTimeMillis() - start + "ms";
    }

    @Getter
    @Builder
    public static class Print {

        private Object[] request;

        private Object response;

    }
}
