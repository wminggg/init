package com.common.config;

import com.common.handler.exception.SystemException;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * RedisConfig
 *
 * @Author: WMING
 * @Date: 2023/07/21
 */

@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    /**
     * Redis连接工厂
     *
     * @return 响应结果
     */
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(redisHost, redisPort);
        return new LettuceConnectionFactory(config);
    }

    /**
     * Redis模板
     *
     * @param connectionFactory Redis连接工厂
     * @return 响应结果
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        template.setDefaultSerializer(new StringRedisSerializer());
        template.setEnableDefaultSerializer(true);

        return template;
    }

    /**
     * 命令行运行器
     *
     * @param redisTemplate Redis模板
     * @return 响应结果
     */
    @Bean
    public CommandLineRunner commandLineRunner(RedisTemplate<String, Object> redisTemplate) {
        return args -> {
            try {
                redisTemplate.opsForValue().get("test");
                logger.info("===Redis 连接成功===");
            } catch (Exception e) {
                logger.error("Redis 连接失败: {}", e.getMessage());
                throw new SystemException(HttpStatus.SC_INTERNAL_SERVER_ERROR, "Redis 连接失败: " + e.getMessage());
            }
        };
    }
}