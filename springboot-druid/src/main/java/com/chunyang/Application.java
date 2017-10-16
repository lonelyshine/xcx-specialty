package com.chunyang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan //扫描servlet 扫描servlet拦截服务
@MapperScan("com.chunyang.mapper") //这里mapper是你的mybatis的mapper目录。
public class Application {
	public static void main(String []args){
		SpringApplication.run(Application.class, args);
	}
}
