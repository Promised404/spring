package com.dpt.test;

import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import com.dpt.bean.Blue;
import com.dpt.bean.Person;
import com.dpt.config.MainConfig2;

public class IOCTest {
	
	ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
	
/*	@Test
	@SuppressWarnings("resource")
	public void test01() {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
		String[] names = applicationContext.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println(name);
		}
	}*/
	
	/*@Test
	@SuppressWarnings("resource")
	public void test02() {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
		System.out.println("ioc容器创建完成....");
		
     	Person person2 = applicationContext.getBean(Person.class);
		Person person = applicationContext.getBean(Person.class);
		Person person = applicationContext.getBean(Person.class);
		Person person2 = applicationContext.getBean(Person.class);
		System.out.println(person == person2);
		String[] names = applicationContext.getBeanDefinitionNames();
		for (String name : names) {
			System.out.println(name);
		}
	}*/

	@Test
	public void Test03() {
		ConfigurableEnvironment environment = (ConfigurableEnvironment) applicationContext.getEnvironment();
		// Windows 10
		String property = environment.getProperty("os.name");
		System.out.println(property);
		String[] beanNamesForType = applicationContext.getBeanNamesForType(Person.class);
		for (String name : beanNamesForType) {
			System.out.println(name);
		}
		Map<String, Person> persons = applicationContext.getBeansOfType(Person.class);
		System.out.println(persons);
	}
	
	@Test
	public void testImport() {
		printBean();
		Blue bean = applicationContext.getBean(Blue.class);
		System.out.println(bean);
		
		//工厂Bean获取的是调用getObject创建的对象
		Object bean2 = applicationContext.getBean("colorFactoryBean");
		Object bean3 = applicationContext.getBean("colorFactoryBean");
		System.out.println("bean的类型" + bean2.getClass());
		System.out.println(bean2 == bean3);
		
		Object bean4 = applicationContext.getBean("&colorFactoryBean");
		System.out.println(bean4.getClass());
	}
	
	public void printBean() {
		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		for (String name : beanDefinitionNames) {
			System.out.println(name);
		}
	}
}
