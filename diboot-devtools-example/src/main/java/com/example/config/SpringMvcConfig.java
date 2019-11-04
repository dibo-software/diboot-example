package com.example.config;

import com.diboot.core.util.DateConverter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring MVC配置
 * @author Mazc
 * @version v2.0
 * @date 2019/11/03
 */
@Configuration
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
@ComponentScan(basePackages={"com.example"})
public class SpringMvcConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new DateConverter());
    }

}