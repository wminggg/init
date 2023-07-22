package com.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.common.constants.RedisKeyConstant;
import com.common.constants.SystemConstants;
import com.common.handler.exception.SystemException;
import com.common.utils.*;
import com.user.mapper.UserMapper;
import com.user.model.dto.UserLoginDto;
import com.user.model.dto.UserRegisterDto;
import com.user.model.entity.UserInfoEntity;
import com.user.service.AuthService;
import com.user.utils.ValidationUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * 身份验证服务impl
 *
 * @Author: WMING
 * @Date: 2023/07/22
 */

@Service("AuthService")
public class AuthServiceImpl extends ServiceImpl<UserMapper, UserInfoEntity>  implements AuthService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public static final String FIELD_NAME_USERNAME = "user_name";
    public static final String FIELD_NAME_NICKNAME = "nickname";
    public static final String FIELD_NAME_EMAIL = "email";
    public static final String FIELD_NAME_PHONE = "phone";

    /**
     * 判断用户信息存在
     *
     * @param fieldName 字段名
     * @param value     价值
     * @return 响应结果
     */
    @Override
    public boolean userInfoExist(String fieldName, Object value) {
        QueryWrapper<UserInfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(fieldName, value);
        return baseMapper.selectCount(queryWrapper) > 0;
    }


    /**
     * 用户注册
     *
     * @param userRegisterDto 用户注册dto
     * @return 响应结果
     */
    @Override
    public void userRegister(UserRegisterDto userRegisterDto) {
        // 数据校验是否输入正确
        String userName = ValidationUtils.validateUserName(userRegisterDto.getUserName());
        String nickname = ValidationUtils.validateNickname(userRegisterDto.getNickname());
        String rawPassword = ValidationUtils.validatePassword(userRegisterDto.getPassword());
        String email = ValidationUtils.validateEmail(userRegisterDto.getEmail());
        String phone = ValidationUtils.validatePhoneNumber(userRegisterDto.getPhone());

        // 判断用户名、昵称、邮箱、电话是否已经存在
        checkIfUserInfoExists(FIELD_NAME_USERNAME, userName, "用户名已存在，请登录或输入其他用户名");
        checkIfUserInfoExists(FIELD_NAME_NICKNAME, nickname, "昵称已存在");
        checkIfUserInfoExists(FIELD_NAME_EMAIL, email, "邮箱已存在，请登录或输入其他邮箱");
        checkIfUserInfoExists(FIELD_NAME_PHONE, phone, "电话已存在，请登录或输入其他电话");

        // 加密密码、邮箱和手机号
        String encryptedPassword = passwordEncoder.encode(rawPassword);

        // 创建 UserInfoEntity 实例
        UserInfoEntity user = new UserInfoEntity();

        // 生成用户ID
        Long userId = SnowFlakeUtils.getInstance().getNum();

        // 生成 Token
        String token = TokenUtils.generateToken(userId);

        // 设置属性值
        user.setUserId(userId);
        user.setUserName(userName);
        user.setPassword(encryptedPassword);
        user.setEmail(email);
        user.setNickname(nickname);
        user.setPhone(phone);
        user.setToken(token);

        // 设置创建和更新人为用户ID
        user.setCreateBy(userId);
        user.setUpdateBy(userId);

        // 插入用户信息
        userMapper.insert(user);

        // 更新 Redis 中的 Token
        updateTokenInRedis(userId, token, user);
    }

    /**
     * 用户登录
     *
     * @param userLoginDto 用户登录dto
     * @return 响应结果
     */
    @Override
    public void userLogin(UserLoginDto userLoginDto) {
        // 校验输入
        String rawPassword = ValidationUtils.validatePassword(userLoginDto.getPassword());
        String identifier = userLoginDto.getIdentifier();

        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(identifier, userLoginDto.getPassword());

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 判断是否认证通过
        if (!authenticate.isAuthenticated()) {
            throw new RuntimeException("用户名或密码错误");
        }

        // 在数据库中查找用户信息
        UserInfoEntity user = findUserInDatabase(identifier);

        if (user != null && passwordEncoder.matches(rawPassword, user.getPassword())) {
            // 生成新的 Token
            String token = TokenUtils.generateToken(user.getUserId());

            // 更新 Redis 中的 Token
            updateTokenInRedis(user.getUserId(), token, user);

            // 登录成功，可以进行后续操作
            // ...
            return;
        }

        // 密码不正确或用户不存在，抛出异常
        throw new SystemException(HttpStatus.SC_UNAUTHORIZED, "用户名或密码错误");
    }

    private void checkIfUserInfoExists(String fieldName, String fieldValue, String errorMessage) {
        if (userInfoExist(fieldName, fieldValue)) {
            throw new SystemException(HttpStatus.SC_CONFLICT, errorMessage);
        }
    }

    private void updateTokenInRedis(Long userId, String token, UserInfoEntity user) {
        // 构造 Redis 键
        String redisKey = RedisKeyConstant.User.USER_TOKEN + userId;

        if (token != null && redisUtils.getTtl(redisKey) <= 0) {
            // Token已过期，重新生成新的Token并更新到Redis
            token = TokenUtils.generateToken(userId);
            // 更新 Redis 中的 Token，并设置过期时间
            redisUtils.setEx(redisKey, token, RedisKeyConstant.Time.DAY, TimeUnit.SECONDS);
        }

        // 保存用户信息到Redis
        redisUtils.setEx(
                RedisKeyConstant.User.USER + userId,
                JSONObject.toJSONString(user),
                RedisKeyConstant.Time.DAY,
                TimeUnit.SECONDS
        );
    }

    public UserInfoEntity findUserInDatabase(String identifier) {
        String fieldName = null;
        String fieldValue = null;

        String identifierType = ValidationUtils.identifierType(identifier);
        if (identifierType != null) {
            switch (identifierType) {
                case "email":
                    fieldName = FIELD_NAME_EMAIL;
                    fieldValue = identifier;
                    break;
                case "phone":
                    fieldName = FIELD_NAME_PHONE;
                    fieldValue = identifier;
                    break;
                case "userName":
                    fieldName = FIELD_NAME_USERNAME;
                    fieldValue = identifier;
                    break;
                default:
                    throw new SystemException(HttpStatus.SC_BAD_REQUEST, "账号输入错误");
            }
        } else {
            throw new SystemException(HttpStatus.SC_BAD_REQUEST, "账号输入错误");
        }

        return findUserByField(fieldName, fieldValue);
    }

    private UserInfoEntity findUserByField(String fieldName, String fieldValue) {
        QueryWrapper<UserInfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(fieldName, fieldValue);
        return userMapper.selectOne(queryWrapper);
    }


    /**
     * 用户注销
     */
    @Override
    public void userLogout(HttpServletRequest request) {
        // 清除认证信息
        SecurityContextHolder.clearContext();

        // 清除 Redis 中的用户 Token
        String token = request.getHeader(SystemConstants.HEADERS.BLOG_TOKEN);
        if (token != null && !token.isEmpty()) {
            Long userId = TokenUtils.getUserId(token);
            String redisTokenKey = RedisKeyConstant.User.USER_TOKEN + userId;
            redisUtils.delete(redisTokenKey);
        }
    }







//    @Override
//    public UserLoginVo userLogin(UserLoginDto userLoginDto) {
//        if (!StringUtils.hasText(userLoginDto.getUserName())) {
//            //提示必须要输入用户名
//            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
//        } else if (!StringUtils.hasText(userLoginDto.getPassword())) {
//            //提示必须要输入用户密码
//            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
//        }
//        UsernamePasswordAuthenticationToken authenticationToken
//                = new UsernamePasswordAuthenticationToken
//                (userLoginDto.getUserName(), userLoginDto.getPassword());
//
//        Authentication authenticate
//                = authenticationManager.authenticate
//                (authenticationToken);
//
//        //判断是否认证通过
//        if(Objects.isNull(authenticate)){
//            throw new RuntimeException("用户名或密码错误");
//        }
//        //获取userid 生成token
//        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
//        String userId = loginUser.getUserInfo().getUserId().toString();
//        String jwt = JwtUtil.createJWT(userId);
//        if(jwt == null){
//            throw new RuntimeException("生成JWT令牌失败");
//        }
//        //把用户信息存入redis
//        redisCache.setCacheObject("login:"+userId,loginUser);
//        //把token和userinfo封装 返回
//        //把User转换成UserInfoVo
//        UserInfoVo userInfoVo
//                = BeanCopyUtils.copyBean
//                (loginUser.getUserInfo(), UserInfoVo.class);
//
//        UserLoginVo userLoginVo = new UserLoginVo(jwt,userInfoVo);
//        return userLoginVo;
//    }
//
//
//
//    @Override
//    public void userRegister(UserRegisterDto userRegisterDto) {
//        // 对数据进行非空判断和存在性判断
//        String userName = userRegisterDto.getUserName();
//        String nickname = userRegisterDto.getNickname();
//        String password = userRegisterDto.getPassword();
//        String Email = userRegisterDto.getEmail();
//
//        //校验：
//        ValidationUtils.validatePassword(password);
//        ValidationUtils.validateEmail(Email);
//        ValidationUtils.validateUserName(userName);
//        ValidationUtils.validateNickname(nickname);
//
//        if (Objects.isNull(nickname) || nickname.trim().isEmpty()) {
//            // 生成昵称的逻辑，例如使用时间戳作为昵称
//            nickname = String.valueOf(System.currentTimeMillis());
//        }
//
//        // 创建 UserInfoEntity 实例
//        UserInfoEntity user = new UserInfoEntity();
//        // 创建 SnowFlakeUtils 实例
//        SnowFlakeUtils snowFlakeUtils = SnowFlakeUtils.getInstance();
//        // 设置属性值
//        user.setUserId(snowFlakeUtils.getNum());
//        user.setUserName(userName);
//        user.setPassword(password);
//        user.setEmail(Email);
//        user.setNickname(nickname);
//
////        user.setPhone(userRegisterDto.getPhone());
////        user.setAvatar(userRegisterDto.getAvatar());
////        user.setSex(userRegisterDto.getSex());
////        user.setIntro(userRegisterDto.getIntro());
//
//        // 存入数据库
//        save(user);
//    }
//
//
//
//    @Override
//    public void userLogout() {
//        //获取token 解析获取userid
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
//        //获取userid
//        Long userId = loginUser.getUserInfo().getUserId();
//        //删除redis中的用户信息
//        redisCache.deleteObject("bloglogin:"+userId);
//    }

}