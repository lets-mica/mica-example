package net.dreamlu.demo.redis.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 订单 DTO
 *
 * @author L.cm
 */
@Data
public class OrderDto implements Serializable {

	/**
	 * 订单 ID
	 */
	private String orderId;

	/**
	 * 用户 ID
	 */
	private String userId;

	/**
	 * 订单金额
	 */
	private Double amount;

	/**
	 * 订单状态
	 */
	private String status;

	/**
	 * 创建时间
	 */
	private Long createTime;
}
