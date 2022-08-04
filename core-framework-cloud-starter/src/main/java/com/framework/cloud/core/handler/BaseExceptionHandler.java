package com.framework.cloud.core.handler;

import com.framework.cloud.common.exception.*;
import com.framework.cloud.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * 异常处理
 *
 * @author wusiwei
 */
@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<Void> exceptionHandle(Exception e) {
        log.error("未知异常：{}" , e);
        return new Result<Void>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<Void> exceptionHandle(BizException e) {
        log.error("业务异常：{}" , e);
        return new Result<Void>(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(CacheException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<Void> exceptionHandle(CacheException e) {
        log.error("缓存异常：{}" , e);
        return new Result<Void>(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(ElasticException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<Void> exceptionHandle(ElasticException e) {
        log.error("查询异常：{}" , e);
        return new Result<Void>(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(EnumException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<Void> exceptionHandle(EnumException e) {
        log.error("枚举异常：{}" , e);
        return new Result<Void>(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(LockException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<Void> exceptionHandle(LockException e) {
        log.error("唯一异常：{}" , e);
        return new Result<Void>(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(OauthException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<Void> exceptionHandle(OauthException e) {
        log.error("认证异常：{}" , e);
        return new Result<Void>(e.getCode(), e.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(HttpStatus.OK)
    public Result<Void> exceptionHandle(MethodArgumentNotValidException e) {
        String errorStr = "";
        BindingResult result = e.getBindingResult();
        if (result.hasErrors()) {
            errorStr = Objects.requireNonNull(result.getFieldError()).getDefaultMessage();
        }
        return new Result<>(HttpStatus.BAD_REQUEST.value(), errorStr);
    }
}
