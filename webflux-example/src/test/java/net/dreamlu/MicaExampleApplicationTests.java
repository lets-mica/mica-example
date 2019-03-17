package net.dreamlu;

import net.dreamlu.mica.test.MicaBootTest;
import net.dreamlu.mica.test.MicaSpringRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 单元测试
 *
 * <p>
 *     1. 多一个 @MicaBootTest("mica-example") 注解
 *     2. @RunWith(MicaSpringRunner.class)，注意注解里的类
 * </p>
 *
 * @author L.cm
 */
@SpringBootTest
@MicaBootTest("mica-example")
@RunWith(MicaSpringRunner.class)
public class MicaExampleApplicationTests {

	@Test
	public void contextLoads() {
	}

}
