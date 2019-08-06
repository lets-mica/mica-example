package net.dreamlu.example;

import net.dreamlu.mica.common.support.IController;
import net.dreamlu.mica.social.AuthRequestFactory;
import net.dreamlu.mica.social.config.AuthSource;
import net.dreamlu.mica.social.model.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SocialWeb implements IController {
	@Autowired
	private AuthRequestFactory authRequestFactory;

	@GetMapping("auth")
	@ResponseBody
	public String auth() {
		AuthSource[] values = AuthSource.values();

		StringBuilder html = new StringBuilder();
		for (AuthSource authSource : values) {
			html.append("<a href=\"/auth/").append(authSource.name().toLowerCase()).append("\">")
				.append(authSource.name()).append("</a>").append("<br>");
		}
		return html.toString();
	}

	@GetMapping("auth/{source}")
	public String auth(@PathVariable String source) {
		return redirect(authRequestFactory.get(source).authorize());
	}

	@GetMapping("callback/{source}")
	@ResponseBody
	public AuthResponse callback(@PathVariable String source, String code) {
		return authRequestFactory.get(source).login(code);
	}
}
