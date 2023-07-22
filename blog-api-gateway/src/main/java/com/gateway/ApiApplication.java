package com.gateway;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * api应用程序
 *
 * @Author: WMING
 * @Date: 2023/07/21
 */

@Slf4j
@SpringBootApplication
@ComponentScan(value = {"com.*"})
@MapperScan("com.*.mapper")
public class ApiApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(ApiApplication.class, args);
        } catch (Exception e) {
            log.error("启动报错：",e);
        }
        log.info("===ApiApplication 启动成功===");
    }
}