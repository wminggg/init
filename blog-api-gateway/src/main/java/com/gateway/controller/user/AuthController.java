package com.gateway.controller.user;

import com.common.annotations.AuthIgnore;
import com.common.model.ResponseResult;
import com.user.model.dto.UserLoginDto;
import com.user.model.dto.UserRegisterDto;
import com.user.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 身份验证控制器
 *
 * @Author: WMING
 * @Date: 2023/07/21
 */

@RestController
@RequestMapping("/user")
public class AuthController {
    @Autowired
    private AuthService authService;

    /**
     * 用户注册
     *
     * @param userRegisterDto 用户注册dto
     * @return 响应结果
     */
    @AuthIgnore
    @PostMapping("/register")
    public ResponseResult<Void> userRegister(@RequestBody UserRegisterDto userRegisterDto){
        authService.userRegister(userRegisterDto);
        return ResponseResult.success("注册成功");
    }

    /**
     * 用户登录
     *
     * @param userLoginDto 用户登录dto
     * @return 响应结果
     */
    @AuthIgnore
    @PostMapping("/login")
    public ResponseResult<Void> userLogin(@RequestBody UserLoginDto userLoginDto){
        authService.userLogin(userLoginDto);
        return ResponseResult.success("登录成功");
    }

    /**
     * 用户注销
     *
     * @return 响应结果
     */
    @PostMapping("/logout")
    public ResponseResult<Void> userLogout(HttpServletRequest request) {
        authService.userLogout(request);
        return ResponseResult.success("注销成功");
    }

}