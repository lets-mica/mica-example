package net.dreamlu.demo.xss;

import io.swagger.annotations.Api;
import net.dreamlu.demo.form.UserForm;
import net.dreamlu.mica.core.result.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;

/**
 * mica-xss 演示
 *
 * @author L.cm
 */
@Validated
@RestController
@Api(tags = "模块::xss示例")
@RequestMapping("xss")
public class XssController {

	@GetMapping("form")
	public R<String> form(@NotEmpty String name) {
		return R.success(name);
	}

	@PostMapping("body")
	public R<UserForm> body(@RequestBody @Validated UserForm form) {
		return R.success(form);
	}

}
