package com.example.iam.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Spring MVC配置
 * @author www.dibo.ltd
 * @version v2.0
 * @date 2019/11/03
 */
@Configuration
@EnableTransactionManagement
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.example.iam"})
@MapperScan(basePackages={"com.example.iam.mapper"})
public class SpringMvcConfig {

    /**
     * Mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }

}