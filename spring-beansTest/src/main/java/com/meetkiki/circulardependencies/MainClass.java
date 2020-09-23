package com.meetkiki.circulardependencies;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.meetkiki.circulardependencies"})
public class MainClass {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainClass.class);

		InstanceA instanceA = (InstanceA) context.getBean("instanceA");
		InstanceB instanceB = (InstanceB) context.getBean("instanceB");


		instanceA.say();
		instanceB.say();

		InstanceC instanceC = (InstanceC) context.getBean("instanceC");
		instanceC.say();
	}

}
