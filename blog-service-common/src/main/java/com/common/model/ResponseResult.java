package com.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.http.HttpStatus;

import java.io.Serializable;

/**
 * 响应结果
 *
 * @Author: WMING
 * @Date: 2023/07/22
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;

    public ResponseResult() {
        this.code = HttpStatus.SC_OK;
        this.message = "Success";
    }

    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResponseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseResult<T> success() {
        return new ResponseResult<>(HttpStatus.SC_OK, "Success");
    }

    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(HttpStatus.SC_OK, "Success", data);
    }

    public static <T> ResponseResult<T> success(String message) {
        return new ResponseResult<>(HttpStatus.SC_OK, message);
    }

    public static <T> ResponseResult<T> success(T data, String message) {
        return new ResponseResult<>(HttpStatus.SC_OK, message, data);
    }

    public static <T> ResponseResult<T> success(Integer code, T data, String message) {
        return new ResponseResult<>(code, message, data);
    }

    public static ResponseResult<?> error(Integer code, String message) {
        return new ResponseResult<>(code, message);
    }

    public static ResponseResult<?> errorResult(Integer code, String message) {
        return new ResponseResult<>(code, message);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
