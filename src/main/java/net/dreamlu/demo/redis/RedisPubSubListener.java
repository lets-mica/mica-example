package net.dreamlu.demo.redis;

import lombok.extern.slf4j.Slf4j;
import net.dreamlu.demo.redis.pojo.OrderDto;
import net.dreamlu.mica.redis.pubsub.RPubSubEvent;
import net.dreamlu.mica.redis.pubsub.RPubSubListener;
import org.springframework.stereotype.Component;

/**
 * Redis PubSub 监听器 - 注解方式
 *
 * @author L.cm
 */
@Slf4j
@Component
public class RedisPubSubListener {

	/**
	 * 监听订单频道
	 */
	@RPubSubListener("order")
	public void onOrder(RPubSubEvent<OrderDto> event) {
		log.info("收到订单消息: channel={}, pattern={}, message={}",
			event.getChannel(), event.getPattern(), event.getMsg());

		OrderDto order = event.getMsg();
		// 处理订单业务逻辑
		log.info("处理订单: orderId={}, userId={}, amount={}",
			order.getOrderId(), order.getUserId(), order.getAmount());
	}

	/**
	 * 监听测试频道
	 */
	@RPubSubListener("test")
	public void onTest(RPubSubEvent<OrderDto> event) {
		log.info("收到测试消息: channel={}, message={}",
			event.getChannel(), event.getMsg());
	}

	/**
	 * 监听用户频道
	 */
	@RPubSubListener("user")
	public void onUser(RPubSubEvent<String> event) {
		log.info("收到用户消息: channel={}, message={}",
			event.getChannel(), event.getMsg());
	}
}
