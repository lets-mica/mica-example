package net.dreamlu.demo.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.demo.redis.pojo.OrderDto;
import net.dreamlu.mica.redis.cache.CacheKey;
import net.dreamlu.mica.redis.cache.MicaRedisCache;
import net.dreamlu.mica.redis.ratelimiter.RateLimiter;
import net.dreamlu.mica.redis.ratelimiter.RateLimiterClient;
import net.dreamlu.mica.redis.stream.RStreamTemplate;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Redis 功能测试 Controller
 *
 * @author L.cm
 */
@Slf4j
@RestController
@RequestMapping("/redis")
@RequiredArgsConstructor
public class RedisController {

	private final MicaRedisCache redisCache;
	private final RateLimiterClient rateLimiterClient;
	private final RedisService redisService;
	private final RStreamTemplate streamTemplate;

	/**
	 * 测试 redis cache 增强 - 使用 @Cacheable 注解
	 * 缓存 5 分钟
	 */
	@GetMapping("/cache/user/{id}")
	public String getCacheUser(@PathVariable String id) {
		return redisService.selectById(id);
	}

	/**
	 * 测试 MicaRedisCache - 简化使用
	 */
	@GetMapping("/cache/simple/{id}")
	public String getSimpleCache(@PathVariable String id) {
		return redisCache.get("simple:user:" + id, () -> {
			log.info("从数据库查询用户: {}", id);
			return "User-" + id + "-Name";
		});
	}

	/**
	 * 测试 MicaRedisCache - 带过期时间
	 */
	@GetMapping("/cache/ttl/{id}")
	public String getCacheWithTtl(@PathVariable String id) {
		// 300 秒过期
		CacheKey cacheKey = new CacheKey("simple:user:" + id, Duration.ofMinutes(5));
		return redisCache.get(cacheKey, () -> {
			log.info("从数据库查询用户: {}", id);
			return "User-" + id + "-Name";
		});
	}

	/**
	 * 测试分布式限流 - 注解方式
	 * 限制每分钟最多 10 次请求
	 */
	@RateLimiter(value = "rate-limiter-test", max = 10, ttl = 1, timeUnit = TimeUnit.MINUTES)
	@GetMapping("/limiter/annotation")
	public String rateLimiterAnnotation() {
		return "请求成功 - 注解限流";
	}

	/**
	 * 测试分布式限流 - 带参数的注解方式
	 */
	@RateLimiter(value = "rate-limiter-user", param = "#userId", max = 5, ttl = 1, timeUnit = TimeUnit.MINUTES)
	@GetMapping("/limiter/user/{userId}")
	public String rateLimiterWithParam(@PathVariable String userId) {
		return "用户 " + userId + " 请求成功";
	}

	/**
	 * 测试分布式限流 - Client 方式
	 */
	@GetMapping("/limiter/client")
	public String rateLimiterClient() {
		String key = "client-limiter-test";
		boolean allowed = rateLimiterClient.isAllowed(key, 10, 60, TimeUnit.SECONDS);
		if (allowed) {
			return "请求成功 - Client 限流";
		} else {
			return "请求被限流";
		}
	}

	/**
	 * 测试分布式限流 - Client allow 方式
	 */
	@GetMapping("/limiter/allow")
	public String rateLimiterAllow() {
		String key = "allow-limiter-test";
		return rateLimiterClient.allow(key, 10, 60, TimeUnit.SECONDS, () -> {
			log.info("执行业务逻辑");
			return "请求成功 - Client Allow 限流";
		});
	}

	/**
	 * 测试 Redis Stream - 发送单个消息
	 */
	@PostMapping("/stream/send")
	public RecordId sendStreamMessage(@RequestBody OrderDto order) {
		return streamTemplate.send("order", order);
	}

	/**
	 * 测试 Redis Stream - 发送带 key 的消息
	 */
	@PostMapping("/stream/send-with-key")
	public RecordId sendStreamMessageWithKey(@RequestBody OrderDto order) {
		return streamTemplate.send("order", "orderId", order);
	}

	/**
	 * 测试 Redis Stream - 批量发送
	 */
	@PostMapping("/stream/send-batch")
	public RecordId sendStreamBatch() {
		Map<String, Object> messages = new HashMap<>();
		messages.put("orderId", "ORDER-001");
		messages.put("userId", "USER-001");
		messages.put("amount", 100.0);
		return streamTemplate.send("order", messages);
	}

	/**
	 * 测试 Redis Stream - 删除消息
	 */
	@DeleteMapping("/stream/delete/{recordId}")
	public Long deleteStreamMessage(@PathVariable String recordId) {
		return streamTemplate.delete("order", recordId);
	}

	/**
	 * 测试 Redis Stream - 修剪流
	 */
	@PostMapping("/stream/trim/{count}")
	public Long trimStream(@PathVariable long count) {
		return streamTemplate.trim("order", count);
	}
}
