package com.dpt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.dpt.bean.Person;
import com.dpt.service.BookService;

//配置类 = 配置文件
@Configuration  //告诉Spring这是一个配置类
//@ComponentScan(value = "com.dpt",excludeFilters = {
//		@Filter(type=FilterType.ANNOTATION,classes={Controller.class,Service.class})
//})
@ComponentScan(value = "com.dpt")/*,includeFilters = {
		@Filter(type=FilterType.ANNOTATION,classes={Controller.class}),
		@Filter(type=FilterType.ASSIGNABLE_TYPE,classes={BookService.class}),
		@Filter(type=FilterType.CUSTOM,classes={MyTypeFilter.class})
},useDefaultFilters = false)*/
//@ComponentScan value:要扫描的包
//excludeFilters = Filter[] : 指定扫描的时候按照扫描规则排除那些组件
//includeFilters = Filter[] : 指定扫描的时候只需要包含哪些组件
//FilterType.ANNOTATION：按照注解
//FilterType.ASSIGNABLE_TYPE:按照给定的类型
//FilterType.ASPECTJ ：是用ASPECTJ
//FilterType.REGEX : 使用正则表达式
public class MainConfig {

	//给容器中注册一个bean;类型为返回值类型，id默认是用方法名
	@Bean(value = "person")
	public Person person01() {
		return new Person(2,"lisi");
	}
	
	@Bean()
	public Person person02() {
		return new Person(3,"lisi2");
	}
	
}
