package com.user.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: WMING
 * @Date: 2023/05/18
 * @Description: 用户登录vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginVo {

    /**
     * 令牌
     */
    private String token;

    /**
     * 用户信息
     */
    private UserInfoVo userInfo;
}