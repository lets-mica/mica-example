package net.dreamlu;

import net.dreamlu.mica.launcher.MicaApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

import javax.servlet.ServletContainerInitializer;

/**
 * 示例 项目
 *
 * @author L.cm
 */
@EnableCaching
@SpringBootApplication
public class MicaExampleApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		MicaApplication.run("mica-example-web", MicaExampleApplication.class, args);
		System.out.println("http://localhost:8083/swagger-ui.html");
	}

	@Override
	protected SpringApplicationBuilder createSpringApplicationBuilder() {
		// war 包启动方式，注意继承 SpringBootServletInitializer，实现 createSpringApplicationBuilder 方法
		return MicaApplication.createSpringApplicationBuilder("mica-example-web", MicaExampleApplication.class);
	}
}
