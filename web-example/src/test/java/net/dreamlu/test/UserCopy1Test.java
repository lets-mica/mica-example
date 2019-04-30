package net.dreamlu.test;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.DebuggingClassWriter;

import java.time.LocalDateTime;

public class UserCopy1Test {

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

		// 2. 初始化 userVo
		UserVo userVo = new UserVo();
		// 3. 构造 BeanCopier，不是用类型转换
		BeanCopier copier = BeanCopier.create(User.class, UserVo.class, false);
		// 4. 拷贝对象，不是用类型转换，转换器可以使用 null
		copier.copy(user, userVo, null);

		// 5. 打印结果：UserVo(name=如梦技术, age=30, birthday=null)
		System.out.println(userVo);
	}
}
