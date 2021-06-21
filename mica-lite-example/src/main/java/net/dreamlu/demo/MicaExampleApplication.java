package net.dreamlu.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 示例 项目
 *
 * @author L.cm
 */
@SpringBootApplication
public class MicaExampleApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MicaExampleApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MicaExampleApplication.class);
	}
}
