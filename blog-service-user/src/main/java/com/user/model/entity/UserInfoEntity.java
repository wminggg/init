package com.user.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * 用户信息实体
 *
 * @Author: WMING
 * @Date: 2023/07/22
 */

@Data
@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@TableName("wm_user_info")
public class UserInfoEntity {
    /**
    * 用户ID;用户唯一标识
    */
    @TableId(value = "user_id", type = IdType.INPUT )
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long userId;

    /**
    * 密码;用户登录密码，经过加密处理后存储
    */
    private String password;

    /**
    * 角色;用户角色：0代表普通用户，1代表管理员
    */
    private Integer role;

    /**
    * 状态;用户账号状态
    */
    private Integer status;

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

    /**
    * 更新时间;用户账号更新的时间
    */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;

    /**
    * 创建时间;用户账号创建的时间
    */
    @TableField(fill = FieldFill.INSERT)
    private Long createTime;

    /**
    * 删除标志;删除标志（0代表未删除，1代表已删除）
    */
    private Integer delFlag;

    /**
    * 创建者
    */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    /**
    * 更新者
    */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;


    /**
     * 令牌
     */
    @TableField(exist = false)
    private String token;

}
