package com.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.common.constants.SystemConstants;
import com.common.handler.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * TokenUtils
 *
 * @Author: WMING
 * @Date: 2023/07/22
 */
@Slf4j
public class TokenUtils {

    public static final String USER_ID = "userId";

    public static final String TIME = "time";

    public static final String TOKEN_SECRET = "djdYMF0C62qkew3bsshVBFz3Ek7nhVSQ";

    public static String generateToken(Long userId) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            Map<String, Object> header = new HashMap<>(2);
            header.put("type", "JWT");
            header.put("alg", "HS256");
            return JWT.create()
                    .withHeader(header)
                    .withClaim(USER_ID, userId)
                    .withClaim(TIME, System.currentTimeMillis())
                    .sign(algorithm);
        } catch (Exception e) {
            log.error("堆栈日志跟踪",e);
            throw new SystemException(HttpStatus.SC_NOT_ACCEPTABLE,"token生成失败");
        }
    }


    public static String getToken(HttpServletRequest request){
        String token = request.getHeader(SystemConstants.HEADERS.BLOG_TOKEN);
        if (StringUtils.isBlank(token)){
            throw new SystemException(HttpStatus.SC_UNAUTHORIZED, "token不合法");
        }
        return token;
    }

    public static Long getUserId(String token) {
        try {
            DecodedJWT decoder = JWT.decode(token);
            Claim claim = decoder.getClaim(USER_ID);
            return claim.asLong();
        } catch (JWTDecodeException e) {
            log.error("堆栈日志跟踪", e);
            throw new SystemException(HttpStatus.SC_UNAUTHORIZED, "token不合法");
        }
    }

    public static Long getUserIdNoThrowEx(HttpServletRequest request) {
        String token = request.getHeader(SystemConstants.HEADERS.BLOG_TOKEN);
        if (StringUtils.isBlank(token)){
            return null;
        }
        return getUserId(token);
    }

    public static Long getUserId(HttpServletRequest request) {
        String token = request.getHeader(SystemConstants.HEADERS.BLOG_TOKEN);
        if (StringUtils.isBlank(token)){
            throw new SystemException(HttpStatus.SC_UNAUTHORIZED, "token不合法");
        }
        return getUserId(token);
    }

    public static Boolean checkToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build();
        try {
            verifier.verify(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}