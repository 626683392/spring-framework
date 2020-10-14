package com.meetkiki.aspectj_aop;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class InstanceB {

	@Autowired
	private InstanceA instanceA;


	public InstanceB() {
		System.out.println("InstanceB实例化");
	}

	@LockAction
	public void say() {
		System.out.println("hello world");
	}

	@Override
	public String toString() {
		return "InstanceB";
	}

}
