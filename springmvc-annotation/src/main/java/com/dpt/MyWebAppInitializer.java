package com.dpt;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.dpt.config.AppConfig;
import com.dpt.config.RootConfig;

//web容器启动的时候创建对象；调用这个方法来初始化容器以及前端控制器
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	//获取根容器的配置类；（spring的配置文件）父容器；
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{RootConfig.class};
	}

	// 获取web容器的配置类（SpringMVC配置文件）子容器；
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{AppConfig.class};
	}

	//获取DispatcherServlet的映射信息
	//	/:拦截所有请求（包括静态资源），但是不包括*.jsp;
	//	/*:拦截所有请求；连*.jsp页面都拦截；jsp页面是tomcat的jsp引擎解析的
	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}

}
