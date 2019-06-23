package com.dpt.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dpt.bean.Person;
import com.dpt.config.MainConfig;

public class Test {
	
	public static void main(String[] args) {
		//通过配置文件的形式展示出来
		//类路径下的配置文件
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
//		Person bean = (Person) applicationContext.getBean("person");
//		System.out.println(bean);
		
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
		Person person = (Person) applicationContext.getBean("person");
		Person person1 = (Person) applicationContext.getBean("person02");
		System.out.println(person);
		System.out.println(person1);
		
		String[] naemsForType = applicationContext.getBeanNamesForType(Person.class);
		for (String name : naemsForType) {
			System.out.println(name);
		}
	}

}
