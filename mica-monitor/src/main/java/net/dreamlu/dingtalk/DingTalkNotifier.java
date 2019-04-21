package net.dreamlu.dingtalk;

import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.domain.events.InstanceStatusChangedEvent;
import de.codecentric.boot.admin.server.domain.values.Registration;
import de.codecentric.boot.admin.server.domain.values.StatusInfo;
import de.codecentric.boot.admin.server.notify.AbstractEventNotifier;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.core.utils.DateUtil;
import org.springframework.core.env.Environment;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * 服务上下线告警
 *
 * <p>
 *     注意：AbstractStatusChangeNotifier 这个事件有毛病
 * </p>
 *
 * @author L.cm
 */
@Slf4j
public class DingTalkNotifier extends AbstractEventNotifier {
	private final DingTalkService dingTalkService;
	private final DingTalkProperties properties;
	private final Environment environment;


	public DingTalkNotifier(DingTalkService dingTalkService, DingTalkProperties properties,
							Environment environment, InstanceRepository repository) {
		super(repository);
		this.dingTalkService = dingTalkService;
		this.properties = properties;
		this.environment = environment;
	}

	@Override
	protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {
		return Mono.fromRunnable(() -> {
			if (event instanceof InstanceStatusChangedEvent) {
				// 构造请求结构
				createAndPushMsg(event, instance);
			}
		});
	}

	private void createAndPushMsg(InstanceEvent event, Instance instance) {
		Registration registration = instance.getRegistration();
		// 服务名
		String appName = registration.getName();
		// 服务地址
		String serviceUrl = registration.getServiceUrl();
		StatusInfo status = instance.getStatusInfo();
		// 时间
		LocalDateTime localDateTime = LocalDateTime.ofInstant(event.getTimestamp(), ZoneId.systemDefault());
		String title = properties.getService().getTitle();

		String message = "## " + title + "\n" +
			"#### **【服务】** " + appName + "\n" +
			"#### **【环境】** " + environment.getActiveProfiles()[0] + "\n" +
			"#### **【地址】** " + serviceUrl + "\n" +
			"#### **【状态】** " + statusCn(status) + "\n" +
			"#### **【时间】** " + DateUtil.formatDateTime(localDateTime) + "\n" +
			"#### **【详情】** " + properties.getLink() + "\n";

		String accessToken = properties.getAccessToken();
		dingTalkService.pushMsg(accessToken, title, message);
	}

	private String statusCn(StatusInfo status) {
		if (status.isUp()) {
			return "应用上线（IS UP）";
		} else if (status.isDown()) {
			return "应用宕机（IS DOWN）";
		} else if (status.isOffline()) {
			return "应用掉线（IS OFFLINE）";
		} else if (status.isUnknown()) {
			return "未知状态（UNKNOWN）";
		} else {
			return "异常状态";
		}
	}
}
