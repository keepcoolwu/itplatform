package cn.betatown.itplatform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.betatown.itplatform.mapper")
public class SpringbootItplatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootItplatformApplication.class, args);
	}
}
