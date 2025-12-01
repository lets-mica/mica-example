package net.dreamlu.demo.redis;

import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.redis.pubsub.RPubSubMessageListener;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.Topic;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Redis PubSub 监听器 - Bean 方式
 *
 * @author L.cm
 */
@Slf4j
@Component
public class RedisPubSubMessageListener implements RPubSubMessageListener {

	@Override
	public List<Topic> getTopics() {
		return List.of(
			ChannelTopic.of("order_1"),
			ChannelTopic.of("order_2")
		);
	}

	@Override
	public void onMessage(Message message, byte[] pattern) {
		String channel = new String(message.getChannel());
		String body = new String(message.getBody());

		if ("order_1".equals(channel)) {
			log.info("处理 order_1 消息: {}", body);
			// 业务逻辑
		}

		if ("order_2".equals(channel)) {
			log.info("处理 order_2 消息: {}", body);
			// 业务逻辑
		}
	}
}
