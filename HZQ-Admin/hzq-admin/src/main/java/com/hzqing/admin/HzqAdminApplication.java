package com.hzqing.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
// 修改默认自动扫描包
@ComponentScan(basePackages = {"com.hzqing"})
@MapperScan("com.hzqing.*.mapper")
public class HzqAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(HzqAdminApplication.class, args);
	}
}
