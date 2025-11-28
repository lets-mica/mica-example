package net.dreamlu.demo.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 用户表单模型
 *
 * @author L.cm
 */
@Data
@Schema(description = "用户表单模型")
public class UserForm {

	@Schema(description = "用户名", example = "<scritp>alert(1)</script>")
	@NotBlank
	private String name;

	@Schema(description = "密码")
	@Size(min = 3, max = 12)
	private String password;
}
