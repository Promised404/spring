package com.dpt.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dpt.bean.Boss;
import com.dpt.bean.Car;
import com.dpt.bean.Color;
import com.dpt.config.MainConfigOfAutowired;
import com.dpt.config.MainConfigOfLifeCycle;
import com.dpt.dao.BookDao;
import com.dpt.service.BookService;

public class IOCTest_Autowired {

	@Test
	public void test01() {
		//1、创建ioc容器
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);
		BookService bookService = applicationContext.getBean(BookService.class);
		System.out.println(bookService);
		
		Boss bean = applicationContext.getBean(Boss.class);
		System.out.println(bean);
		
		Car bean2 = applicationContext.getBean(Car.class);
		System.out.println(bean2);
		Color bean3 = applicationContext.getBean(Color.class);
		System.out.println(bean3);
		//BookDao bean = applicationContext.getBean(BookDao.class);
		//System.out.println(bean);
		System.out.println(applicationContext);
		applicationContext.close();
	}
	
}
