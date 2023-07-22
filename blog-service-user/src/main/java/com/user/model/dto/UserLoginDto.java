package com.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户登录dto
 *
 * @Author: WMING
 * @Date: 2023/07/22
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {
    /**
     * 密码;用户登录密码，经过加密处理后存储
     */
    private String password;

    /**
     * 标识符,结合userName、email、phone
     */
    private String identifier;

}
