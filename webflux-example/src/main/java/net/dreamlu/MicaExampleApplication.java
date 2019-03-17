package net.dreamlu;

import net.dreamlu.mica.launcher.MicaApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 示例 项目
 *
 * @author L.cm
 */
@EnableCaching
@SpringBootApplication
public class MicaExampleApplication {

	public static void main(String[] args) {
		MicaApplication.run("mica-example", MicaExampleApplication.class, args);
		System.out.println("http://localhost:8080/swagger-ui.html");
	}
}
