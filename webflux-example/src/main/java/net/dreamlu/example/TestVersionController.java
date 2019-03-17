package net.dreamlu.example;

import io.swagger.annotations.Api;
import net.dreamlu.mica.annotation.ApiVersion;
import net.dreamlu.mica.annotation.UrlVersion;
import net.dreamlu.mica.annotation.VersionMapping;
import net.dreamlu.mica.core.result.R;
import net.dreamlu.mica.core.result.SystemCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 版本演示控制器
 *
 * <p>
 *     文档详见：https://www.dreamlu.net/#/doc/boot-version
 * </p>
 *
 * @author L.cm
 */
@RestController
@VersionMapping(urlVersion = "v1-1")
@Api(description = "接口版本演示")
public class TestVersionController {

	/**
	 * 测试版本共存
	 * 优先使用方法上的版本v1-0
	 */
	@GetMapping("example1")
	@UrlVersion("v1-0")
	public R<String> test10() {
		return R.fail(SystemCode.DATA_ADD_FAILED);
	}

    /**
     * 测试版本共存
     * 方法上没有版本时，使用类注册上的版本号v1-1
     */
    @GetMapping("example1")
    public R<String> test11() {
        return R.fail(SystemCode.DATA_ADD_FAILED);
    }

	/**
	 * 测试版本共存
	 * 优先使用方法上的版本v1.1
	 */
	@GetMapping("example2")
	@ApiVersion("v1.1")
	public R<String> test20() {
		return R.fail(SystemCode.DATA_UPDATE_FAILED);
	}

	/**
	 * 测试版本共存
	 * 优先使用方法上的版本v2.0
	 */
	@PostMapping("example2")
	@ApiVersion("v2.0")
	public R<Map<String, Object>> test20(@RequestBody Map<String, Object> map) {
		return R.success(map);
	}

}
