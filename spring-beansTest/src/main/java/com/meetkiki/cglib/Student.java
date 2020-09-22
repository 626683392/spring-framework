package com.meetkiki.cglib;

public class Student {

	private String name = "zhangsan";

	public String getStuName() {
		hi();
		return name;
	}

	public void hi(){
		System.out.println("hi");
	}

}
