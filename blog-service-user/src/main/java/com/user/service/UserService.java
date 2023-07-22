package com.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.user.model.dto.UserInfoDto;
import com.user.model.entity.UserInfoEntity;
import com.user.model.vo.UserInfoVo;

/**
 * 用户服务
 *
 * @Author: WMING
 * @Date: 2023/07/22
 */
public interface UserService extends IService<UserInfoEntity> {

    /**
     * 用户信息
     *
     * @return 响应结果
     */
    UserInfoVo userInfo();

    /**
     * 更新用户信息
     *
     * @param userInfoDto 用户信息dto
     * @return 响应结果
     */
    void updateUserInfo(UserInfoDto userInfoDto);



}