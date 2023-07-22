package com.common.handler.security;

import com.alibaba.fastjson.JSON;
import com.common.model.ResponseResult;
import com.common.utils.WebUtils;
import org.apache.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证入口点impl
 *
 * @Author: WMING
 * @Date: 2023/07/22
 */

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        authException.printStackTrace();

        ResponseResult<?> result = null;
        if (authException instanceof BadCredentialsException) {
            result = ResponseResult.errorResult(HttpStatus.SC_UNAUTHORIZED, authException.getMessage());
        } else if (authException instanceof InsufficientAuthenticationException) {
            result = ResponseResult.errorResult(HttpStatus.SC_UNAUTHORIZED, "需要登录");
        } else {
            result = ResponseResult.errorResult(HttpStatus.SC_INTERNAL_SERVER_ERROR, "认证或授权失败");
        }
        // 响应给前端
        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}

