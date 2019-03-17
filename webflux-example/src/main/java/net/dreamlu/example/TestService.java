package net.dreamlu.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.concurrent.Future;

@Service
@Slf4j
public class TestService {

	/**
	 * 演示 spring cache 注解的使用
	 *
	 * value 对应 `resources/config/ehcache.xml` 中的cache名
	 *
	 * key 对应cache key
	 *
	 * 详细文档请查看：
	 *
	 * Spring缓存注解@Cache,@CachePut , @CacheEvict，@CacheConfig使用
	 *
	 * https://blog.csdn.net/sanjay_f/article/details/47372967
	 */
	@Cacheable(value = "hour", key = "#id")
	public String selectById(Serializable id) {
		log.info("selectById");
		return "selectById:" + id;
	}

	public Integer getTestId() {
		return 100000;
	}

	/**
	 * 最简单的异步调用，返回值为void
	 *
	 * 添加 @Async 注解即可
	 */
	@Async
	public void asyncSimplest() {
		// 注意查看本 日志打印
		log.info("asyncSimplest");
	}

	/**
	 * 有返回值的异步调用
	 * 1. 添加 @Async
	 * 2. 返回值包装为 Future
	 */
	@Async
	public Future<String> asyncSimplesReturn(Serializable id) {
		// 注意查看本 日志打印
		log.info("asyncSimplesReturn");
		return new AsyncResult<>("hhhh:" + id);
	}
}
