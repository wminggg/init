//package com.user.utils;
//
//import com.user.model.entity.LoginUser;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//
///**
// * @Author: WMING
// * @Date: 2023/05/19
// * @Description: 安全配置工具类
// */
//public class SecurityUtils
//{
//
//    /**
//     * 获取用户
//     **/
//    public static LoginUser getLoginUser()
//    {
//        return (LoginUser) getAuthentication().getPrincipal();
//    }
//
//    /**
//     * 获取Authentication
//     */
//    public static Authentication getAuthentication() {
//        return SecurityContextHolder.getContext().getAuthentication();
//    }
//
//    public static Boolean isAdmin(){
//        Long id = getLoginUser().getUserInfo().getUserId();
//        return id != null && 1L == id;
//    }
//
//    public static Long getUserId() {
//        return getLoginUser().getUserInfo().getUserId();
//    }
//}