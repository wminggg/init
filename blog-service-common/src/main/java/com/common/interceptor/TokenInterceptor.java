package com.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.common.annotations.AuthIgnore;
import com.common.constants.RedisKeyConstant;
import com.common.constants.SystemConstants;
import com.common.handler.exception.SystemException;
import com.common.utils.RedisUtils;
import com.common.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 令牌拦截器
 *
 * @Author: WMING
 * @Date: 2023/07/22
 */
@Slf4j
@Component
@Order(1)
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AuthIgnore authIgnore;
        if (handler instanceof HandlerMethod) {
            authIgnore = ((HandlerMethod) handler).getMethodAnnotation(AuthIgnore.class);
        } else {
            return true;
        }

        //检查方法上是否有 AuthIgnore 注解，有则不拦截
        if (authIgnore != null) {
            return true;
        }

        // 获取请求中的 Token
        String token = request.getHeader(SystemConstants.HEADERS.BLOG_TOKEN);

        // 检查 Token 是否存在
        if (token == null || token.isEmpty()) {
            writeFailure(response);
            return false;
        }

        // 验证 Token 是否有效
        if (!TokenUtils.checkToken(token)) {
            writeFailure(response);
            return false;
        }

        // 从 Token 中获取用户ID
        Long userId = TokenUtils.getUserId(token);

        // 构造 Redis 键
        String redisKey = RedisKeyConstant.User.USER + userId;

        // 检查 Token 是否存在于 Redis 中
        if (!redisUtils.hasKey(redisKey)) {
            writeFailure(response);
            return false;
        }

        // 刷新 Token 的过期时间,设置过期时间，单位为天
        redisUtils.expire(redisKey, RedisKeyConstant.Time.DAY, TimeUnit.SECONDS);

//        // 将用户ID存储到请求中，便于后续使用
//        request.setAttribute("userId", userId);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 在此处可以进行一些后置处理操作
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            // 记录请求处理的结果
            // 将请求的路径、请求参数、响应状态码等信息记录到日志中
            // 获取请求中的 Token
            String token = request.getHeader(SystemConstants.HEADERS.BLOG_TOKEN);

            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Class<?> controllerClass = handlerMethod.getBeanType();
            Method method = handlerMethod.getMethod();

            log.info("===============request start===============");
            log.info("request uri: {}", request.getRequestURI());
            log.info("request method: {}", request.getMethod());
            log.info("token: {}", token);
            log.info("controller: {}", controllerClass.getName());
            log.info("method: {}", method.getName());
            log.info("IP address: {}", request.getRemoteAddr());
            log.info("request parameters: {}", getRequestParamString(request));
            log.info("===============request end===============");

            // 处理异常情况
            if (ex != null) {
                log.error("请求处理过程中发生异常: {}", ex.getMessage());
                // 进行异常处理的逻辑，例如返回自定义错误页面或处理特定的错误码等
                if (ex instanceof SystemException) {
                    SystemException systemException = (SystemException) ex;
                    response.setStatus(systemException.getCode());
                    response.getWriter().write(systemException.getMsg());
                } else {
                    // 处理其他类型的异常
                    response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().write("服务器内部错误");
                }
            }
        } catch (Exception e) {
            // 处理异常情况的异常处理逻辑
            // 例如，记录异常日志或返回通用的错误信息等
            log.error("在执行afterCompletion时发生异常: {}", e.getMessage());
        }
    }

    private String getRequestParamString(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String key = entry.getKey();
            String[] values = entry.getValue();
            sb.append(key).append(": ");
            if (values.length > 1) {
                sb.append(Arrays.toString(values));
            } else {
                sb.append(values[0]);
            }
            sb.append(", ");
        }
        if (sb.length() > 0) {
            sb.delete(sb.length() - 2, sb.length());
        }
        return sb.toString();
    }

    private void writeFailure(HttpServletResponse response) throws IOException {
        JSONObject result = new JSONObject();
        result.put("code", HttpStatus.SC_UNAUTHORIZED);
        result.put("msg", "凭证非法或身份过期，请重新登录！");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.println(result);
        writer.flush();
        writer.close();
    }
}
