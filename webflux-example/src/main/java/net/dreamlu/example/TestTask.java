package net.dreamlu.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * spring task 定时任务测试，适用于单系统
 * 注意：不适合用于集群
 * @author L.cm
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TestTask {
	private final CacheManager cacheManager;
	private final TestService testService;

	/**
	 * spring task cron 定时任务
	 *
	 * cron 表达式详见：https://www.cnblogs.com/liuyitian/p/4108391.html
	 */
	@Scheduled(cron = "0 1/2 * * * ?")
	public void cronTest() {
		//1. 测试注解，详见 selectById 上的注释
		// 注意查看日志答应，2，3都没有 mybatis的查询日志
		testService.selectById(1L);
		log.debug("testService1");
		testService.selectById(1L);
		log.debug("testService2");
		testService.selectById(1L);
		log.debug("testService3");

		//2. 测试手动存储cache
		Cache cache = cacheManager.getCache("hour");
		// Callable 参数：当 key 为x的缓存不纯在的时候 获取数据，并保持到cache中
		Integer x1 = cache.get("x", testService::getTestId);
		log.debug("x1: {}", x1);

		//3. java 8 可以写成下面的形式
		Integer x2 = cache.get("x", testService::getTestId);

		log.debug("x2: {}", x2);

		//4. spring Async异步，spring中我们不需要手动创建线程，spring task可以轻松实现异步方法
		//4.1 没有返回值的异步
		testService.asyncSimplest();

		//4.2 有返回值的异步调用
		Future<String> asyneFuture = testService.asyncSimplesReturn(1);
		try {
			String info = asyneFuture.get();
			log.info(info);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

	}


}
