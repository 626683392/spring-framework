package com.meetkiki.aspectj_aop;


import org.springframework.stereotype.Component;


@Component
public class InstanceA {

	public InstanceA() {
		System.out.println("InstanceA实例化");
	}

	public void say() {
		//System.out.println(this);
		say2();
		System.out.println("hello world");
	}


	@LockAction
	public void say2() {
		//System.out.println(this);
		System.out.println("hello world");
	}


}
