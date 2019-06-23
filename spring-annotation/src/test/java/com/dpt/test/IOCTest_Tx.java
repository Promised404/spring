package com.dpt.test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dpt.tx.TxConfig;
import com.dpt.tx.UserService;

public class IOCTest_Tx {

	@Test
	public void test01() {
		//1、创建ioc容器
		AnnotationConfigApplicationContext applicationContext =
				new AnnotationConfigApplicationContext(TxConfig.class);
		
		UserService userService = applicationContext.getBean(UserService.class);
		
		userService.insertUser();
		
		applicationContext.close();
	}
	
}
