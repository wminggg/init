package com.gateway.controller.user;

import com.common.annotations.AuthIgnore;
import com.common.model.ResponseResult;
import com.user.model.dto.UserInfoDto;
import com.user.model.vo.UserInfoVo;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器
 *
 * @Author: WMING
 * @Date: 2023/07/21
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取用户信息
     *
     * @return 响应结果
     */
    @AuthIgnore
    @GetMapping("/getUserInfo")
    public ResponseResult<UserInfoVo> userInfo(){
        UserInfoVo userInfoVo = userService.userInfo();
        return ResponseResult.success(userInfoVo, "获取用户信息成功");
    }

    /**
     * 更新用户信息
     *
     * @param userInfoDto 用户信息dto
     * @return 响应结果
     */
    @AuthIgnore
    @PutMapping("/updateUserInfo")
    public ResponseResult<Void> updateUserInfo(@RequestBody UserInfoDto userInfoDto){
        userService.updateUserInfo(userInfoDto);
        return ResponseResult.success("更新用户信息成功");
    }
}