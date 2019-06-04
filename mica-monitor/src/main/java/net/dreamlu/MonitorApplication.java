package net.dreamlu;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import net.dreamlu.dingtalk.DingTalkProperties;
import net.dreamlu.mica.launcher.MicaApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * spring boot admin 监控服务
 *
 * @author L.cm
 */
@SpringCloudApplication
@EnableAdminServer
@EnableConfigurationProperties(DingTalkProperties.class)
public class MonitorApplication {

	public static void main(String[] args) {
		MicaApplication.run("mica-monitor", MonitorApplication.class, args);
	}

}
