package net.dreamlu;

import net.dreamlu.mica.test.MicaBootTest;
import net.dreamlu.mica.test.MicaSpringRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(MicaSpringRunner.class)
@MicaBootTest(appName = "mica-monitor")
public class MonitorApplicationTests {

	@Test
	public void contextLoads() {
	}

}
