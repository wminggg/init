package com.user.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * 用户注册dto
 *
 * @Author: WMING
 * @Date: 2023/07/22
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto {

    /**
     * 用户名;用户的登录账号名
     */
    private String userName;

    /**
     * 昵称;昵称，用户可自行修改
     */
    private String nickname;

    /**
     * 邮箱;用户绑定的邮箱，经过加密处理后存储
     */
    private String email;

    /**
     * 密码;用户登录密码，经过加密处理后存储
     */
    private String password;

    /**
     * 电话;用户绑定的手机号，经过加密处理后存储
     */
    private String phone;

}
