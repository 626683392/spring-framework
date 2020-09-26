package com.meetkiki.configurationtest;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);

		InstanceB instanceB = (InstanceB) context.getBean("instanceB");

		instanceB.say();
	}

}
