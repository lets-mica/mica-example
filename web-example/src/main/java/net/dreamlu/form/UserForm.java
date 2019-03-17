package net.dreamlu.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 用户表单模型
 *
 * @author L.cm
 */
@Data
@ApiModel("用户表单模型")
public class UserForm {

	@ApiModelProperty("用户名")
	@NotBlank
	private String name;
}
