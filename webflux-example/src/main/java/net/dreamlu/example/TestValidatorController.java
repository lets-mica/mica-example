package net.dreamlu.example;

import net.dreamlu.form.UserForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.dreamlu.mica.core.result.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * 参数校验演示
 *
 * @author L.cm
 */
@Validated
@RestController
@RequestMapping("validator")
@Api(description = "参数校验演示")
public class TestValidatorController {

	@ApiOperation("演示基础类型-校验")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "name", required = true, value = "用户名")
	})
	@PostMapping("primitive")
	public R<String> primitiveParam(@NotEmpty String name) {
		return R.success(name);
	}

	@ApiOperation("演示 java bean 类型-校验")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "name", required = true, value = "用户名")
	})
	@PostMapping("bean")
	public R<UserForm> formParam(@Valid UserForm form) {
		return R.success(form);
	}

}
