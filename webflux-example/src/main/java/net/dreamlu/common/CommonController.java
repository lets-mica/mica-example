package net.dreamlu.common;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.dreamlu.mica.captcha.reactive.MicaCaptchaReactive;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 通用控制器演示
 *
 * @author L.cm
 */
@RestController
@Api(description = "通用控制器演示")
@RequiredArgsConstructor
public class CommonController {
	private final MicaCaptchaReactive micaCaptcha;

	@ApiOperation("图片验证码")
	@GetMapping("captcha.jpg")
	public Mono<ResponseEntity<Resource>> captcha(ServerWebExchange exchange) {
		return Mono.just(micaCaptcha.generate(exchange));
	}

}
