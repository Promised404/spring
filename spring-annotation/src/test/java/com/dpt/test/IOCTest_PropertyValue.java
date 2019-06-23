package com.dpt.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import com.dpt.bean.Person;
import com.dpt.config.MainConfigOfPropertyValues;

public class IOCTest_PropertyValue {

	ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfPropertyValues.class);
	
	@Test
	public void test01() {
		printBean();
		Person person = (Person) applicationContext.getBean("person");
		System.out.println(person);
		
		Environment environment = applicationContext.getEnvironment();
		String property = environment.getProperty("person.nickName");
		System.out.println(property);
	}
	
	public void printBean() {
		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		for (String name : beanDefinitionNames) {
			System.out.println(name);
		}
	}
}
