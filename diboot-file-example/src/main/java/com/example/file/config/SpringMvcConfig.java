/*
 * Copyright (c) 2015-2020, www.dibo.ltd (service@dibo.ltd).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.example.file.config;

import com.diboot.core.util.DateConverter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/***
 * Spring配置文件
 * @author www.dibo.ltd
 * @version v2.0
 * @date 2019/1/19
 */
@Configuration
@EnableTransactionManagement
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.example.file"})
@MapperScan(basePackages={"com.example.file.mapper"})
public class SpringMvcConfig implements WebMvcConfigurer{

    @Override
    public void addFormatters(FormatterRegistry registry) {
       registry.addConverter(new DateConverter());
    }

}