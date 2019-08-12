package com.example.config;

import com.diboot.core.util.DateConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/***
 * Spring配置文件
 * @author Mazhicheng
 * @version v2.0
 * @date 2019/1/19
 */
@Configuration
@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
@EnableTransactionManagement(proxyTargetClass=true)
@ComponentScan(basePackages={"com.example"})
public class SpringMvcConfig implements WebMvcConfigurer{
    private static final Logger log = LoggerFactory.getLogger(SpringMvcConfig.class);

    @Override
    public void addFormatters(FormatterRegistry registry) {
       registry.addConverter(new DateConverter());
    }

}