package net.dreamlu.example;

import lombok.AllArgsConstructor;
import net.dreamlu.mica.common.support.IController;
import net.dreamlu.mica.social.config.AuthConfig;
import net.dreamlu.mica.social.model.AuthResponse;
import net.dreamlu.mica.social.request.AuthDingTalkRequest;
import net.dreamlu.mica.social.request.AuthRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
public class SocialWeb implements IController {
	private final AuthDingTalkRequest authDingTalkRequest;

	@GetMapping("auth")
	public String auth() {
//		AuthRequest authRequest = new AuthDingTalkRequest(AuthConfig.builder()
//			.clientId("dingoab7pkxxteufaoc50x")
//			.clientSecret("pclr4hldAHhhlZxQmsxdLNMhrYpl5bnCFmX56zAYT8BgdqDF-dVhCORfaTZoO2hz")
//			.redirectUri("http://frp.dreamlu.net/callback")
//			.build());
		// 返回授权页面，可自行调整
		return redirect(authDingTalkRequest.authorize());
	}

	@GetMapping("callback")
	@ResponseBody
	public AuthResponse callback(String code) {
//		AuthRequest authRequest = new AuthDingTalkRequest(AuthConfig.builder()
//			.clientId("dingoab7pkxxteufaoc50x")
//			.clientSecret("pclr4hldAHhhlZxQmsxdLNMhrYpl5bnCFmX56zAYT8BgdqDF-dVhCORfaTZoO2hz")
//			.redirectUri("http://www.dreamlu.net/api/dingding/callback")
//			.build());
		// 授权登录后会返回一个code，用这个code进行登录
		return authDingTalkRequest.login(code);
	}
}
