package net.dreamlu;

import lombok.RequiredArgsConstructor;
import net.dreamlu.mica.core.result.R;
import net.dreamlu.mica.core.result.SystemCode;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 授权过滤器
 *
 * @author dream.lu
 */
@Component
@RequiredArgsConstructor
public class AuthFilter implements GlobalFilter, Ordered {
	private final WebClient lbWebClient;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// TODO 此处为测试代码
		return lbWebClient.post().uri("http://mica-monitor/orders/find?id=1")
			.exchange()
			.flatMap(clientResponse -> clientResponse.bodyToMono(R.class))
			.flatMap(result -> {
				if (R.isSuccess(result)) {
					return chain.filter(exchange);
				}
				// 抛出业务错误
				R.throwFail(SystemCode.FAILURE);
				return Mono.empty();
			});
	}

	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}
}
