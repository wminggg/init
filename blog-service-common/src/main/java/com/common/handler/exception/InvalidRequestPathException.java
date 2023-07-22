package com.common.handler.exception;

/**
 * 无效请求路径异常
 *
 * @Author: WMING
 * @Date: 2023/07/22
 */
public class InvalidRequestPathException extends RuntimeException {
    public InvalidRequestPathException(String message) {
        super(message);
    }
}