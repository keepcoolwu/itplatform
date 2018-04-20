package cn.betatown.itplatform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@MapperScan(basePackages = { "cn.betatown.itplatform.mapper", "cn.betatown.itplatform.security.mapper" })
public class SpringbootItplatformApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringbootItplatformApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootItplatformApplication.class, args);
	}
}
