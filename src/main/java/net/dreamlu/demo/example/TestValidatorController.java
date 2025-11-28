package net.dreamlu.demo.example;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import net.dreamlu.demo.form.UserForm;
import net.dreamlu.mica.core.result.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 参数校验演示
 *
 * @author L.cm
 */
@Validated
@RestController
@RequestMapping("validator")
@Tag(name = "参数校验演示")
public class TestValidatorController {

	@Operation(summary = "演示基础类型-校验")
	@PostMapping("primitive")
	public R<String> primitiveParam(@NotEmpty String name) {
		return R.success(name);
	}

	@Operation(summary = "演示 java bean 类型-校验")
	@PostMapping("bean")
	public R<UserForm> formParam(@Valid UserForm form) {
		return R.success(form);
	}

}
