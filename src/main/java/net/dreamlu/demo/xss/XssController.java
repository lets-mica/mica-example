package net.dreamlu.demo.xss;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import net.dreamlu.demo.form.UserForm;
import net.dreamlu.mica.core.result.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * mica-xss 演示
 *
 * @author L.cm
 */
@Validated
@RestController
@Tag(name = "模块::xss示例")
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
