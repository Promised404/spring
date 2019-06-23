package com.dpt.test;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dpt.bean.Yellow;
import com.dpt.config.MainConfigOfProfile;

public class IOCTest_Profile {
	
	//1、使用命令行参数的方式进行切换,在虚拟机参数位置加载 _Dspring.profiles.active=test
	//2、代码激活某种环境；
	@Test
	public void test01() {
//		AnnotationConfigApplicationContext applicationContext = 
//				new AnnotationConfigApplicationContext(MainConfigOfProfile.class);
		//1、创建一个applicationContext
		AnnotationConfigApplicationContext applicationContext = 
				new AnnotationConfigApplicationContext();
		//2、设置需要激活的环境
		applicationContext.getEnvironment().setActiveProfiles("dev");
		//3、注册主配置类
		applicationContext.register(MainConfigOfProfile.class);
		//4、启动刷新容器
		applicationContext.refresh();
		
		String[] namesForType = applicationContext.getBeanNamesForType(DataSource.class);
		for (String string : namesForType) {
			System.out.println(string);
		} 
		
		Yellow bean = applicationContext.getBean(Yellow.class);
		System.out.println(bean);
	}
	
}
