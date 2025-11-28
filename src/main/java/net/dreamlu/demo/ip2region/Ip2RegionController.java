package net.dreamlu.demo.ip2region;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import net.dreamlu.mica.core.result.R;
import net.dreamlu.mica.core.utils.WebUtil;
import net.dreamlu.mica.ip2region.core.Ip2regionSearcher;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * mica-xss 演示
 *
 * @author L.cm
 */
@Validated
@RestController
@RequestMapping("ip")
@Tag(name = "模块::ip2region示例")
@RequiredArgsConstructor
public class Ip2RegionController {
	private final Ip2regionSearcher searcher;

	@GetMapping("region")
	public R<String> region() {
		// 注意： ipInfo 可能为 null
//		IpInfo ipInfo = searcher.memorySearch(WebUtil.getIP());
		return R.success(searcher.getAddress(WebUtil.getIP()));
	}

	@GetMapping("region-by-ip")
	public R<String> regionByIp(@NotBlank String ip) {
		return R.success(searcher.getAddress(ip));
	}

}
