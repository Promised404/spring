package com.dpt.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 监听项目的启动和销毁
 * @author 邓鹏涛
 *
 */
public class UserListener implements ServletContextListener{

	//监听ServletContext销毁
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("UserListener...contextDestroyed...");
	}

	//监听ServletContext启动初始化
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("UserListener...contextInitialized...");
	}

}
