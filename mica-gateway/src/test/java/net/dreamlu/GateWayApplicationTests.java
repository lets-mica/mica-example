package net.dreamlu;

import net.dreamlu.mica.test.MicaBootTest;
import net.dreamlu.mica.test.MicaSpringRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(MicaSpringRunner.class)
@MicaBootTest(appName = "mica-gateway")
public class GateWayApplicationTests {

	@Test
	public void contextLoads() {
	}

}
