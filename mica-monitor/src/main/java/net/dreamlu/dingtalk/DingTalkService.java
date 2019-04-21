package net.dreamlu.dingtalk;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * 钉钉 服务
 *
 * @author L.cm
 */
@Slf4j
@RequiredArgsConstructor
public class DingTalkService {
	private static final String DING_TALK_ROBOT_URL = "https://oapi.dingtalk.com/robot/send";
	private final RestTemplate restTemplate;

	/**
	 * 发送消息
	 * @param accessToken token
	 * @param title title
	 * @param text 消息
	 */
	public void pushMsg(String accessToken, String title, String text) {
		log.info("钉钉告警消息：[创建消息体]title:{}, text:{}", title, text);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

		HashMap<String, String> params = new HashMap<>(2);
		params.put("title", title);
		params.put("text", text);

		Map<String, Object> messageJson = new HashMap<>(2);
		messageJson.put("msgtype", "markdown");
		messageJson.put("markdown", params);
		log.info("创建消息体 json：{}", messageJson);

		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(messageJson, headers);
		URI uri = UriComponentsBuilder.fromUriString(DING_TALK_ROBOT_URL)
			.queryParam("access_token", accessToken)
			.build()
			.toUri();
		String result = this.restTemplate.postForObject(uri, entity, String.class);
		log.info("钉钉告警消息：[消息返回]result:{}", result);
	}
}
