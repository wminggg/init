package com.common.handler.exception;

import com.common.model.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常
 *
 * @Author: WMING
 * @Date: 2023/07/21
 */

@RestControllerAdvice
@Slf4j
public class GlobalException {
    @ExceptionHandler(SystemException.class)
    public ResponseResult<?> systemExceptionHandler(SystemException e) {
        log.error("出现了异常: {}", e.getMessage());
        return ResponseResult.errorResult(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult<?> exceptionHandler(Exception e) {
        log.error("出现了异常: {}", e.getMessage());
        return ResponseResult.errorResult(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(InvalidRequestPathException.class)
    public ResponseResult<?> handleInvalidRequestPathException(InvalidRequestPathException e) {
        log.error("出现了异常: {}", e.getMessage());
        return ResponseResult.errorResult(HttpStatus.SC_BAD_REQUEST, e.getMessage());
    }
}

