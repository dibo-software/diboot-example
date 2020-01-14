package com.example.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.diboot.core.util.DateConverter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring MVC配置
 * @author www.dibo.ltd
 * @version v2.0
 * @date 2019/11/03
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.example"})
@MapperScan(basePackages={"com.example.mapper"})
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