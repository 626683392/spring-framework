package com.meetkiki.configurationtest;


import org.springframework.stereotype.Component;


@Component
public class InstanceB {

	private InstanceA instanceA;

	public InstanceB() {
		System.out.println("InstanceB实例化");
	}

	public void say() {
		System.out.println("hello world");
	}

	@Override
	public String toString() {
		return "InstanceB";
	}


	public void setInstanceA(InstanceA instanceA) {
		this.instanceA = instanceA;
	}
}
