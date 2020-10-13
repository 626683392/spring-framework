package com.meetkiki.aspectj_aop;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(exposeProxy = true)
@ComponentScan
public class MainClass {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainClass.class);

		context.setAllowCircularReferences(true);

		InstanceA instanceA = (InstanceA) context.getBean("instanceA");

		instanceA.say();
	}


}
