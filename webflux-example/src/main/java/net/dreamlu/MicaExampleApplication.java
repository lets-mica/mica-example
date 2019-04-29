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
		MicaApplication.run("mica-example-webflux", MicaExampleApplication.class, args);
		System.out.println("http://localhost:8084");
	}
}
