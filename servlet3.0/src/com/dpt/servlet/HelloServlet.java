package com.dpt.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(Thread.currentThread() + "start...");
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=UTF-8");
		try {
			sayHello();
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.getWriter().write("<p>我好啊！！！</p>");
		System.out.println(Thread.currentThread() + "end...");
	}
	
	public void sayHello() throws Exception {
		System.out.println(Thread.currentThread() + "processing...");
		Thread.sleep(3000);
	}
}
