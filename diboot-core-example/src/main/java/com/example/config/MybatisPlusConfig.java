package com.example.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.diboot.core.handler.DataAccessControlInteceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis-plus配置
 * @author www.dibo.ltd
 * @version v1.0
 * @date 2020/10/28
 */
@Configuration
public class MybatisPlusConfig {
    /**
     * 配置拦截器
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //interceptor.addInnerInterceptor(new DataAccessControlInteceptor());
        return interceptor;
    }

}
