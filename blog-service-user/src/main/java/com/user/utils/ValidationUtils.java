package com.user.utils;

import com.common.handler.exception.SystemException;
import org.apache.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * ValidationUtils
 *
 * @Author: WMING
 * @Date: 2023/07/22
 */
public class ValidationUtils {

    private static final int MIN_PASSWORD_LENGTH = 6;
    private static final int MAX_PASSWORD_LENGTH = 12;
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String PHONE_NUMBER_REGEX = "^(\\+?\\d{1,4}[-\\s]?)?\\d{1,13}$";
    private static final int MIN_NICKNAME_LENGTH = 2;
    private static final int MAX_NICKNAME_LENGTH = 20;
    private static final String NICKNAME_REGEX = "^[\\p{L}0-9_-]+$";

    public static void validateNonNull(String value) {
        if (!StringUtils.hasText(value)) {
            throw new SystemException(HttpStatus.SC_BAD_REQUEST, "值不能为空");
        }
    }

    /**
     * 标识符类型
     *
     * @param identifier 标识符
     * @return 响应结果
     */
    public static String identifierType(String identifier) {
        if (StringUtils.hasText(identifier)) {
            if (identifier.matches(EMAIL_REGEX)) {
                return "email";
            } else if (identifier.matches(PHONE_NUMBER_REGEX)) {
                return "phone";
            } else if (identifier.matches(NICKNAME_REGEX)) {
                return "userName";
            }
        }
        return null;
    }

    /**
     * 验证密码
     *
     * @param password 密码
     * @return 响应结果
     */
    public static String validatePassword(String password) {
        if (!StringUtils.hasText(password)) {
            throw new SystemException(HttpStatus.SC_BAD_REQUEST, "密码不能为空");
        }
        if (password.length() < MIN_PASSWORD_LENGTH || password.length() > MAX_PASSWORD_LENGTH) {
            throw new SystemException(HttpStatus.SC_BAD_REQUEST, "密码长度必须在6到12个字符之间");
        }
        if (!password.matches(PASSWORD_REGEX)) {
            throw new SystemException(HttpStatus.SC_BAD_REQUEST, "密码格式无效,请输入包含大小写英文字母以及数字的密码");
        }
        return password;
    }

    /**
     * 验证电子邮件
     *
     * @param email 电子邮件
     * @return 响应结果
     */
    public static String validateEmail(String email) {
        if (!StringUtils.hasText(email)) {
            throw new SystemException(HttpStatus.SC_BAD_REQUEST, "邮箱不能为空");
        }
        if (!email.matches(EMAIL_REGEX)) {
            throw new SystemException(HttpStatus.SC_BAD_REQUEST, "邮箱格式无效");
        }

        return email;
    }

    /**
     * 验证电话号码
     *
     * @param phoneNumber 电话号码
     * @return 响应结果
     */
    public static String validatePhoneNumber(String phoneNumber) {
        if (!StringUtils.hasText(phoneNumber)) {
            throw new SystemException(HttpStatus.SC_BAD_REQUEST, "手机号不能为空");
        }
        if (!phoneNumber.matches(PHONE_NUMBER_REGEX)) {
            throw new SystemException(HttpStatus.SC_BAD_REQUEST, "手机号格式无效");
        }

        return phoneNumber;
    }

    /**
     * 验证昵称
     *
     * @param nickname 昵称
     * @return 响应结果
     */
    public static String validateNickname(String nickname) {
        if (Objects.isNull(nickname)) {
            // 允许昵称为空白或不填写
            return null;
        }
        return getString(nickname);
    }

    /**
     * 验证用户名
     *
     * @param userName
     * @return 响应结果
     */
    public static String validateUserName(String userName) {
        if (Objects.isNull(userName)) {
            throw new SystemException(HttpStatus.SC_BAD_REQUEST, "用户名不能为空");
        }
        return getString(userName);
    }

    /**
     * 得到字符串
     *
     * @param userName 用户名
     * @return 响应结果
     */
    private static String getString(String userName) {
        if (userName.trim().isEmpty()) {
            throw new SystemException(HttpStatus.SC_BAD_REQUEST, "昵称格式无效");
        }
        if (!userName.matches(NICKNAME_REGEX)) {
            throw new SystemException(HttpStatus.SC_BAD_REQUEST, "昵称格式无效");
        }
        if (userName.length() < MIN_NICKNAME_LENGTH || userName.length() > MAX_NICKNAME_LENGTH) {
            throw new SystemException(HttpStatus.SC_BAD_REQUEST, "昵称长度必须在2到20个字符之间");
        }
        return userName;
    }
}

