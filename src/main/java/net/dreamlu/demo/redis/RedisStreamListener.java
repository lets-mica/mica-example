package net.dreamlu.demo.redis;

import lombok.extern.slf4j.Slf4j;
import net.dreamlu.demo.redis.pojo.OrderDto;
import net.dreamlu.mica.redis.stream.MessageModel;
import net.dreamlu.mica.redis.stream.RStreamListener;
import net.dreamlu.mica.redis.stream.ReadOffsetModel;
import org.springframework.data.redis.connection.stream.Record;
import org.springframework.stereotype.Component;

/**
 * Redis Stream 监听器 - 注解方式
 *
 * @author L.cm
 */
@Slf4j
@Component
public class RedisStreamListener {

	/**
	 * 监听订单消息 - 集群模式
	 */
	@RStreamListener(
		name = "order",
		messageModel = MessageModel.CLUSTERING,
		offsetModel = ReadOffsetModel.LAST_CONSUMED,
		autoAcknowledge = true
	)
	public void onOrderMessage(Record<String, OrderDto> record) {
		log.info("收到订单消息 - 集群模式: recordId={}, value={}", record.getId(), record.getValue());
		OrderDto order = record.getValue();
		// 处理订单业务逻辑
		log.info("处理订单: orderId={}, userId={}, amount={}",
			order.getOrderId(), order.getUserId(), order.getAmount());
	}

	/**
	 * 监听订单消息 - 广播模式
	 * 所有订阅者都会收到消息
	 */
	@RStreamListener(
		name = "order-broadcast",
		messageModel = MessageModel.BROADCASTING,
		autoAcknowledge = true
	)
	public void onOrderBroadcast(Record<String, OrderDto> record) {
		log.info("收到订单广播消息: recordId={}, value={}", record.getId(), record.getValue());
	}

	/**
	 * 监听订单消息 - 从头开始读取
	 */
	@RStreamListener(
		name = "order-from-start",
		offsetModel = ReadOffsetModel.START,
		autoAcknowledge = false
	)
	public void onOrderFromStart(Record<String, OrderDto> record) {
		log.info("收到订单消息(从头读): recordId={}, value={}", record.getId(), record.getValue());
		// 手动 ACK 的逻辑在业务处理完成后执行
	}

}
