package net.dreamlu.demo.captcha;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.dreamlu.mica.captcha.service.ICaptchaService;
import net.dreamlu.mica.captcha.vo.CaptchaVo;
import net.dreamlu.mica.core.utils.StringUtil;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * mica-captcha 示例
 *
 * @author L.cm
 */
@RestController
@RequestMapping("captcha")
@Api(tags = "模块::captcha示例")
@RequiredArgsConstructor
public class CaptchaController {
	private final ICaptchaService captchaService;

	@ApiOperation("图片验证码-jpg")
	@GetMapping(value = "captcha.jpg", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<Resource> captchaJpg(HttpSession session) {
		// 注意：图片验证码可以采用 cookie + cache 或 session 存储的方法
		String uuid = StringUtil.getUUID();
		session.setAttribute("captcha", uuid);
		return captchaService.generateResponseEntity(uuid);
	}

	@ApiOperation("图片验证码-base64")
	@GetMapping("captcha.json")
	public CaptchaVo captchaJson() {
		// 可以采用 cache 或 session 存储 uuid
		String uuid = StringUtil.getUUID();
		return captchaService.generateBase64Vo(uuid);
	}

}
