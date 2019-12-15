package springcore01.classcode.com.gupaoedu.demo.service.impl;

import springcore01.classcode.com.gupaoedu.mvcframework.annotation.GPService;
import springcore01.classcode.com.gupaoedu.demo.service.IDemoService;

/**
 * 核心业务逻辑
 */
@GPService
public class DemoService implements IDemoService{

	public String get(String name) {
		return "My name is " + name;
	}

}
