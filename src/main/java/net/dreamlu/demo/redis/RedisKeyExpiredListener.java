package net.dreamlu.demo.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisKeyExpiredEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Redis Key 过期事件监听器
 * 需要在配置中开启: mica.redis.key-expired-event.enable=true
 *
 * @author L.cm
 */
@Slf4j
@Component
public class RedisKeyExpiredListener {

	/**
	 * 监听 Redis Key 过期事件
	 */
	@Async
	@EventListener(RedisKeyExpiredEvent.class)
	public void onRedisKeyExpired(RedisKeyExpiredEvent<Object> event) {
		String redisKey = new String(event.getId());
		log.info("Redis Key 已过期: {}", redisKey);
		
		// 可以根据 key 的前缀进行不同的业务处理
		if (redisKey.startsWith("user:")) {
			log.info("处理用户缓存过期事件: {}", redisKey);
			// 用户缓存过期的业务逻辑
		} else if (redisKey.startsWith("order:")) {
			log.info("处理订单缓存过期事件: {}", redisKey);
			// 订单缓存过期的业务逻辑
		}
	}
}
