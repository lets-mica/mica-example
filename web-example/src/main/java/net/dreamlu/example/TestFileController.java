package net.dreamlu.example;

import io.swagger.annotations.Api;
import net.dreamlu.mica.core.result.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * 参数校验演示
 *
 * @author L.cm
 */
@Validated
@RestController
@RequestMapping("file")
@Api(description = "文件上传演示")
public class TestFileController {

	@PostMapping
	public R<String> upload(InputStream files) {
		return R.success();
	}
}
