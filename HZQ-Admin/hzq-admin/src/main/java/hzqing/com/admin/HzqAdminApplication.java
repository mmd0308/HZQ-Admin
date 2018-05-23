package hzqing.com.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableSwagger2
@EnableTransactionManagement
@ServletComponentScan
@MapperScan(basePackages = "hzqing.com.admin.mapper")
public class HzqAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(HzqAdminApplication.class, args);
	}
}
