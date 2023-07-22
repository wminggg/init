package com.common.handler.security;

import com.alibaba.fastjson.JSON;
import com.common.model.ResponseResult;
import com.common.utils.WebUtils;
import org.apache.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 拒绝访问处理器impl
 *
 * @Author: WMING
 * @Date: 2023/07/22
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        accessDeniedException.printStackTrace();
        ResponseResult<?> result = ResponseResult.errorResult(HttpStatus.SC_UNAUTHORIZED, "未授权");
        //响应给前端
        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}