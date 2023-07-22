package com.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.user.model.dto.UserLoginDto;
import com.user.model.dto.UserRegisterDto;
import com.user.model.entity.UserInfoEntity;

import javax.servlet.http.HttpServletRequest;


/**
 * 身份验证服务
 *
 * @Author: WMING
 * @Date: 2023/07/22
 */

public interface AuthService extends IService<UserInfoEntity> {

    /**
     * 判断用户信息存在
     *
     * @param fieldName 字段名
     * @param value     价值
     * @return 响应结果
     */
    boolean userInfoExist(String fieldName, Object value);

    /**
     * 用户登录
     *
     * @param userLoginDto 用户登录dto
     * @return 响应结果
     */
    void userLogin(UserLoginDto userLoginDto);

    /**
     * 用户注册
     *
     * @param userRegisterDto 用户注册dto
     * @return 响应结果
     */
    void userRegister(UserRegisterDto userRegisterDto);

    /**
     * 用户注销
     *
     * @param request 请求
     * @return 响应结果
     */
    void userLogout(HttpServletRequest request);
}