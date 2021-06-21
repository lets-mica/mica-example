package net.dreamlu.test;

import net.dreamlu.mica.core.utils.BeanUtil;
import org.springframework.cglib.core.DebuggingClassWriter;

import java.time.LocalDateTime;

public class UserCopy3Test {

	public static void main(String[] args) {
		// 设置 cglib 源码生成目录
		String sourcePath = "/Users/lcm/git/mica/mica-example/web-example/src/test/java";
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, sourcePath);

		// 1. 初始化 user，赋值
		User user = new User();
		user.setId(250);
		user.setName("如梦技术");
		user.setAge(30);
		user.setBirthday(LocalDateTime.now());

		// 2. 使用 mica 的 BeanUtil copy 方法
		UserVo userVo = BeanUtil.copy(user, UserVo.class);

		// 3. 打印结果：UserVo(name=如梦技术, age=30, birthday=null)
		System.out.println(userVo);
	}
}
