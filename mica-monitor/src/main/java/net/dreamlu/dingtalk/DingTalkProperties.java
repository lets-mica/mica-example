package net.dreamlu.dingtalk;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 钉钉配置
 *
 * @author L.cm
 */
@Getter
@Setter
@RefreshScope
@ConfigurationProperties("dingtalk")
public class DingTalkProperties {
	/**
	 * 钉钉机器人 token
	 */
	private String accessToken;

	/**
	 * 地址配置
	 */
	private String link;

	private Service service = new Service();
	private Error error = new Error();

	@Getter
	@Setter
	public static class Service {
		/**
		 * 服务 状态 title
		 */
		private String title = "服务状态通知";
	}

	@Getter
	@Setter
	public static class Error {
		/**
		 * 异常 告警 title
		 */
		private String title = "服务异常告警";
	}
}
