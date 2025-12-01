package net.dreamlu.demo.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Redis 服务示例
 *
 * @author L.cm
 */
@Slf4j
@Service
public class RedisService {

	/**
	 * 测试缓存注解
	 * user#5m 表示缓存名为 user，过期时间为 5 分钟
	 */
	@Cacheable(value = "user#5m", key = "#id")
	public String selectById(Serializable id) {
		log.info("selectById: {}", id);
		// 模拟数据库查询
		return "User-" + id + "-Data";
	}

	/**
	 * 测试缓存 - 10 秒过期
	 */
	@Cacheable(value = "user#10s", key = "#id")
	public String selectByIdShortTtl(Serializable id) {
		log.info("selectByIdShortTtl: {}", id);
		return "User-" + id + "-Short";
	}

	/**
	 * 测试缓存 - 1 小时过期
	 */
	@Cacheable(value = "user#1h", key = "#id")
	public String selectByIdLongTtl(Serializable id) {
		log.info("selectByIdLongTtl: {}", id);
		return "User-" + id + "-Long";
	}

	/**
	 * 测试缓存 - 1 天过期
	 */
	@Cacheable(value = "user#1d", key = "#id")
	public String selectByIdVeryLongTtl(Serializable id) {
		log.info("selectByIdVeryLongTtl: {}", id);
		return "User-" + id + "-VeryLong";
	}

	/**
	 * 测试缓存 - 毫秒级过期 (500 毫秒)
	 */
	@Cacheable(value = "user#500ms", key = "#id")
	public String selectByIdMillis(Serializable id) {
		log.info("selectByIdMillis: {}", id);
		return "User-" + id + "-Millis";
	}
}
