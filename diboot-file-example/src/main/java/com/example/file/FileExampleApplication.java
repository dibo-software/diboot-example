package com.example.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 应用配置
 * @author www.dibo.ltd
 * @version v2.0
 * @date 2019/1/19
 */
@SpringBootApplication
public class FileExampleApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(FileExampleApplication.class, args);
	}

}