package com.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.user.mapper.UserMapper;
import com.user.model.entity.User;
import com.user.model.entity.UserInfoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * 用户详细信息服务impl
 *
 * @Author: WMING
 * @Date: 2023/07/22
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final UserMapper userMapper;

    public UserDetailsServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 加载用户用户名
     *
     * @param identifier 标识符
     * @return 响应结果
     */
    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        try {
            // 根据用户名或邮箱或手机号查询用户信息
            LambdaQueryWrapper<UserInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.and(wrapper -> wrapper.eq(UserInfoEntity::getUserName, identifier)
                    .or().eq(UserInfoEntity::getEmail, identifier)
                    .or().eq(UserInfoEntity::getPhone, identifier));
            UserInfoEntity userInfoEntity = userMapper.selectOne(queryWrapper);

            // 判断是否查到用户，如果没查到抛出异常
            if (userInfoEntity == null) {
                throw new UsernameNotFoundException("用户不存在");
            }

            // 创建Spring Security的UserDetails对象
            return new User(userInfoEntity.getUserName(), userInfoEntity.getPassword(), Collections.emptyList());
        } catch (Exception e) {
            log.error("根据用户名加载用户信息失败", e);
            throw new UsernameNotFoundException("加载用户信息失败");
        }
    }

}
