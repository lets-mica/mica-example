package net.dreamlu.demo.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.demo.redis.pojo.OrderDto;
import net.dreamlu.mica.redis.pubsub.RPubSubPublisher;
import org.springframework.web.bind.annotation.*;

/**
 * Redis PubSub 测试 Controller
 *
 * @author L.cm
 */
@Slf4j
@RestController
@RequestMapping("/redis/pubsub")
@RequiredArgsConstructor
public class RedisPubSubController {

	private final RPubSubPublisher pubSubPublisher;

	/**
	 * 发布消息到指定频道
	 */
	@PostMapping("/publish/{channel}")
	public String publish(@PathVariable String channel, @RequestBody OrderDto message) {
		pubSubPublisher.publish(channel, message);
		log.info("发布消息到频道 {}: {}", channel, message);
		return "消息已发布";
	}

	/**
	 * 发布订单消息
	 */
	@PostMapping("/order")
	public String publishOrder(@RequestBody OrderDto order) {
		pubSubPublisher.publish("order", order);
		log.info("发布订单消息: {}", order);
		return "订单消息已发布";
	}

	/**
	 * 发布测试消息
	 */
	@PostMapping("/test")
	public String publishTest(@RequestBody OrderDto message) {
		pubSubPublisher.publish("test", message);
		log.info("发布测试消息: {}", message);
		return "测试消息已发布";
	}
}
