package net.dreamlu.demo.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 用户表单模型
 *
 * @author L.cm
 */
@Data
@Schema(name = "用户表单模型")
public class UserForm {

	@Schema(name = "用户名")
	@NotBlank
	private String name;
}
