package net.dreamlu.common;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.dreamlu.mica.captcha.Captcha;
import net.dreamlu.mica.captcha.servlet.MicaCaptchaServlet;
import net.dreamlu.mica.core.result.R;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 通用控制器演示
 *
 * @author L.cm
 */
@RestController
@Api(description = "通用控制器演示")
@RequiredArgsConstructor
public class CommonController {
	private final MicaCaptchaServlet micaCaptcha;

	@ApiOperation("图片验证码-jpg图片")
	@GetMapping(value = "captcha.jpg", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<Resource> captchaJpg(HttpServletResponse response) {
		return micaCaptcha.generate(response);
	}

	@ApiOperation("图片验证码-base64")
	@GetMapping("captcha.json")
	public R<Captcha> captchaJson() {
		return R.success(micaCaptcha.generateBase64());
	}
}
