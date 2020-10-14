package com.meetkiki.customerimportselector;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.type.AnnotationMetadata;

@EnableMethodCostTime
@ComponentScan
public class MainClass {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainClass.class);

		ServiceA serviceA = (ServiceA) context.getBean("serviceA");

		serviceA.say();
	}

}
