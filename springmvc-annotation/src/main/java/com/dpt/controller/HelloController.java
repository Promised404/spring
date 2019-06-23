package com.dpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dpt.service.HelloService;

@Controller
public class HelloController {
	
	@Autowired
	private HelloService helloService;
	
	@ResponseBody
	@RequestMapping(value = "/hello")
	public String hello() {
		String sayHello = helloService.sayHello("tomcat...");
		return sayHello;
	}

	@RequestMapping(value = "/success")
	public String success() {
		return "success";
	}
	
}
