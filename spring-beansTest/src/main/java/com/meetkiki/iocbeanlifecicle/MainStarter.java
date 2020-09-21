package com.meetkiki.iocbeanlifecicle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MainStarter {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);

		Person person = (Person) context.getBean("person");

		System.out.println("person:"+person);
	}
}
