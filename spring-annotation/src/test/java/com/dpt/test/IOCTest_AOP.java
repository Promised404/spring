package com.dpt.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dpt.aop.MathCalculator;
import com.dpt.bean.Boss;
import com.dpt.bean.Car;
import com.dpt.bean.Color;
import com.dpt.config.MainConfigAOP;
import com.dpt.config.MainConfigOfAutowired;
import com.dpt.config.MainConfigOfLifeCycle;
import com.dpt.dao.BookDao;
import com.dpt.service.BookService;

public class IOCTest_AOP {

	@Test
	public void test01() {
		//1、创建ioc容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigAOP.class);
		
		//不要自己创建对象，要用spring中的对象
		MathCalculator mathCalculator = applicationContext.getBean(MathCalculator.class);
		mathCalculator.div(1, 0);
		
		applicationContext.close();
	}
	
}
