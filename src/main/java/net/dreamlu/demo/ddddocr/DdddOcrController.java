package net.dreamlu.demo.ddddocr;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.dreamlu.mica.ddddocr.core.OCREngine;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * mica-ddddocr 示例
 *
 * @author L.cm
 */
@RestController
@RequestMapping("ddddocr")
@Tag(name = "模块::ddddocr示例")
@RequiredArgsConstructor
public class DdddOcrController {
	private final OCREngine ocrEngine;

	@Operation(summary = "验证码识别")
	@PostMapping(value = "detectImage")
	public String detectImage(MultipartFile file) throws IOException {
		byte[] bytes = file.getBytes();
		return ocrEngine.detectImage(bytes);
	}

}
