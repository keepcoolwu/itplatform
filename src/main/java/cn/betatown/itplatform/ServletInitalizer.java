package cn.betatown.itplatform;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class ServletInitalizer extends SpringBootServletInitializer {

	@Override
	protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
		return application.sources(SpringbootItplatformApplication.class);
	}
}