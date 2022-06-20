package com.framework.cloud.core;

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
