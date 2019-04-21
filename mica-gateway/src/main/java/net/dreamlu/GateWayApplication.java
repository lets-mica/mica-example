package net.dreamlu;

import net.dreamlu.mica.launcher.MicaApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 网关
 *
 * @author L.cm
 */
@SpringCloudApplication
public class GateWayApplication {

	public static void main(String[] args) {
		MicaApplication.run("mica-gateway", GateWayApplication.class, args);
	}

}
