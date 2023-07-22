package com.common.config;


import com.common.Security.MyPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * 安全配置
 *
 * @Author: WMING
 * @Date: 2023/07/21
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            // 关闭CSRF安全协议
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
                 // 对于登录接口，允许匿名访问
                .antMatchers("/login").anonymous()
                // 对于注册接口，允许匿名访问
                .antMatchers("/register").anonymous()
                // 对于注册接口，允许匿名访问
                .anyRequest().permitAll()
            .and()
            .formLogin()
                // 设置登录页面地址
                .loginPage("/Login")
                // 设置用户名参数名称
                .usernameParameter("userName")
                // 设置密码参数名称
                .passwordParameter("password")
                // 设置登录逻辑请求地址
                .loginProcessingUrl("/login")
                // 设置登录失败后的转发地址
                .failureForwardUrl("/Failure")
                // 设置登录成功后的转发地址
                .successForwardUrl("/Home")
            .and()
            .logout()
                // 回收HttpSession对象
                .invalidateHttpSession(true)
                // 清空Security记录的用户登录标记
                .clearAuthentication(true)
                // 配置退出成功后进入的请求地址
                .logoutSuccessUrl("/Home")
                // 配置退出成功后进入的请求地址
                .logoutUrl("/logout")
            .and()
            .exceptionHandling()
                // 配置认证入口点
                .authenticationEntryPoint(authenticationEntryPoint)
                // 配置访问拒绝处理器
                .accessDeniedHandler(accessDeniedHandler)
            .and()
            // 关闭默认的注销功能
            .logout().disable()
            // 允许跨域
            .cors();
    }


    /**
     * 密码编码器
     *
     * @return 响应结果
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new MyPasswordEncoder();
    }

}
