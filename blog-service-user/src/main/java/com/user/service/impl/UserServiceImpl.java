package com.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.common.constants.SystemConstants;
import com.common.utils.BeanCopyUtils;
import com.common.utils.TokenUtils;
import com.user.model.entity.UserInfoEntity;
import com.user.utils.ValidationUtils;
import com.user.mapper.UserMapper;
import com.user.model.dto.UserInfoDto;
import com.user.model.vo.UserInfoVo;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import static com.common.utils.ObjectUtils.setIfNotNull;


/**
 * 用户服务impl
 *
 * @Author: WMING
 * @Date: 2023/07/22
 */

@Service("UserService")
public class UserServiceImpl extends ServiceImpl<UserMapper, UserInfoEntity> implements UserService {

    @Autowired
    private HttpServletRequest request;

    private final String token = SystemConstants.HEADERS.BLOG_TOKEN;

    /**
     * 用户信息
     *
     * @return 响应结果
     */
    @Override
    public UserInfoVo userInfo() {
        //获取当前用户id
        Long userId = TokenUtils.getUserId(request.getHeader(token));
        //根据用户id查询用户信息
        UserInfoEntity userInfoEntity = getById(userId);
        //封装成UserInfoVo
        return BeanCopyUtils.copyBean(userInfoEntity,UserInfoVo.class);
    }


    /**
     * 更新用户信息
     *
     * @param userInfoDto 用户信息dto
     * @return 响应结果
     */
    @Override
    public void updateUserInfo(UserInfoDto userInfoDto) {
        Long userId = TokenUtils.getUserId(request.getHeader(token));

        // 创建 UpdateWrapper 实例
        UpdateWrapper<UserInfoEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id", userId);

        // 邮箱和电话校验
        String email = ValidationUtils.validateEmail(userInfoDto.getEmail());
        String phone = ValidationUtils.validatePhoneNumber(userInfoDto.getPhone());

        // 设置需要更新的字段和对应的值（如果非空）
        updateWrapper.set("email", email);
        updateWrapper.set("phone", phone);
        setIfNotNull(userInfoDto.getNickname(), nickname -> updateWrapper.set("nickname", nickname));
        setIfNotNull(userInfoDto.getAvatar(), avatar -> updateWrapper.set("avatar", avatar));
        setIfNotNull(userInfoDto.getSex(), sex -> updateWrapper.set("sex", sex));
        setIfNotNull(userInfoDto.getIntro(), intro -> updateWrapper.set("intro", intro));

        // 执行更新操作
        update(updateWrapper);
    }
}