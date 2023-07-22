package com.user.model.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: WMING
 * @Date: 2023/05/18
 * @Description: 用户信息vo
 */

@Data
@Accessors(chain = true)
public class UserInfoVo {
    /**
     * 用户ID;用户唯一标识
     */
    private Long userId;

    /**
     * 昵称;昵称，用户可自行修改
     */
    private String nickname;

    /**
     * 头像;用户头像
     */
    private String avatar;

    /**
     * 邮箱;用户绑定的邮箱，经过加密处理后存储
     */
    private String email;

    /**
     * 电话;用户绑定的手机号，经过加密处理后存储
     */
    private String phone;

    /**
     * 用户名;用户的登录账号名
     */
    private String userName;

    /**
     * 性别;性别，0代表女性，1代表男性, 2代表未知
     */
    private Integer sex;

    /**
     * 简介;用户简介，用户可自行填写
     */
    private String intro;

}
