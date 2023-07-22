package com.common.Security;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

import com.common.handler.exception.SystemException;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 密码编码器
 *
 * @Author: WMING
 * @Date: 2023/07/22
 */

public class MyPasswordEncoder implements PasswordEncoder {
    private static final int SALT_LENGTH = 6;

    @Override
    public String encode(CharSequence rawPass) {
        try {
            String salt = generateSalt();
            byte[] encryptedBytes = sha256Digest(rawPass.toString().getBytes(StandardCharsets.UTF_8));
            assert encryptedBytes != null;
            // 将盐值与加密后的密码一起存储
            String encodedPassword = byteArrayToHexString(encryptedBytes);
            return mergePasswordAndSalt(encodedPassword ,salt);
        } catch (Exception e) {
            throw new SystemException(HttpStatus.SC_INTERNAL_SERVER_ERROR, "密码加密失败");
        }
    }

    @Override
    public boolean matches(CharSequence rawPass, String encodedPass) {
        try {
            // 提取加密后的密码中的盐值
            String extractedSalt = extractSaltFromEncodedPassword(encodedPass);
            byte[] encryptedBytes = sha256Digest(rawPass.toString().getBytes(StandardCharsets.UTF_8));
            assert encryptedBytes != null;
            String encryptedPassword = byteArrayToHexString(encryptedBytes);
            // 提取加密后的密码部分（不包含盐值）
            String  mergePasswordAndSalt = mergePasswordAndSalt(encryptedPassword, extractedSalt);
            return mergePasswordAndSalt.equals(encodedPass);
        } catch (Exception e) {
            throw new SystemException(HttpStatus.SC_INTERNAL_SERVER_ERROR, "密码匹配失败");
        }
    }




    private String generateSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] saltBytes = new byte[SALT_LENGTH];
        secureRandom.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }

    private byte[] sha256Digest(byte[] input) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            return messageDigest.digest(input);
        } catch (NoSuchAlgorithmException e) {
            throw new SystemException(HttpStatus.SC_INTERNAL_SERVER_ERROR, "获取SHA-256消息摘要失败");
        }
    }

    private String mergePasswordAndSalt(String password, String salt) {
        password = password == null ? "" : password;
        return salt != null && !salt.isEmpty() ? password + salt : password;
    }

    private String byteArrayToHexString(byte[] bytes) {
        StringBuilder resultSb = new StringBuilder();
        for (byte b : bytes) {
            resultSb.append(String.format("%02x", b));
        }
        return resultSb.toString();
    }

    private String extractSaltFromEncodedPassword(String encodedPass) {
        // 从加密后的密码中提取盐值的逻辑
        // 假设加密后的密码形式为 "passwordsalt"
        int saltIndex = encodedPass.length() - (SALT_LENGTH + 2);
        if (saltIndex > 0) {
            return encodedPass.substring(saltIndex);
        }
        return null;
    }
}
