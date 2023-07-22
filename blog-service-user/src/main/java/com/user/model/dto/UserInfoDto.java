package com.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户信息dto
 *
 * @Author: WMING
 * @Date: 2023/07/22
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto {
    /**
     * 邮箱;用户绑定的邮箱，经过加密处理后存储
     */
    private String email;

    /**
     * 电话;用户绑定的手机号，经过加密处理后存储
     */
    private String phone;

    /**
     * 昵称;昵称，用户可自行修改
     */
    private String nickname;

    /**
     * 头像;用户头像
     */
    private String avatar;

    /**
     * 性别;性别，0代表女性，1代表男性, 2代表未知
     */
    private Integer sex;

    /**
     * 简介;用户简介，用户可自行填写
     */
    private String intro;
}