package com.dpt.bean;

import org.springframework.beans.factory.annotation.Value;

public class Person {
	
	//使用@Value对bean里面的属性进行赋值
	//1.基本的赋值
	//2.可以使用SpEL;#{}
	//3.可以写${}；取出配置文件【properties】中的值（在运行环境变量里的值）
	
	
	@Value("#{12*21}")
	private Integer id;
	@Value("雪")
	private String name;
	@Value("${person.nickName}")
	private String nickName;
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", nickName=" + nickName + "]";
	}
	public Person(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Person() {
		super();
	}
	
}
