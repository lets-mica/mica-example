package net.dreamlu.dingtalk;

import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

/**
 * 钉钉自动配置
 *
 * @author L.cm
 */
@Configuration
@RequiredArgsConstructor
public class DingTalkConfiguration {
	private final InstanceRepository repository;
	private final Environment environment;

	@Bean
	@ConditionalOnMissingBean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public DingTalkService dingTalkService(RestTemplate restTemplate) {
		return new DingTalkService(restTemplate);
	}

	@Bean
	public DingTalkNotifier dingTalkNotifier(DingTalkProperties props, DingTalkService dingTalkService) {
		return new DingTalkNotifier(dingTalkService, props, environment, repository);
	}

}
