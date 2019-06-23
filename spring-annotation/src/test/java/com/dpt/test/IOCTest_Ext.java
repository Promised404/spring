package com.dpt.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dpt.aop.MathCalculator;
import com.dpt.bean.Boss;
import com.dpt.bean.Car;
import com.dpt.bean.Color;
import com.dpt.config.MainConfigAOP;
import com.dpt.config.MainConfigOfAutowired;
import com.dpt.config.MainConfigOfLifeCycle;
import com.dpt.dao.BookDao;
import com.dpt.ext.ExtConfig;
import com.dpt.service.BookService;

public class IOCTest_Ext {

	@Test
	public void test01() {
		//1、创建ioc容器
		AnnotationConfigApplicationContext applicationContext = 
				new AnnotationConfigApplicationContext(ExtConfig.class);
		
//		applicationContext.getBean(requiredType)
		applicationContext.publishEvent(new ApplicationEvent(new String("我发布的事件")) {
		});
		
		applicationContext.close();
	}
	
}
