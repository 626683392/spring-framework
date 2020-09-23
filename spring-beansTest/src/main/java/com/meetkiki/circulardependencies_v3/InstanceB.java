package com.meetkiki.circulardependencies_v3;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class InstanceB {

	@Autowired
	private InstanceA instanceA;


	public InstanceB() {
		System.out.println("InstanceB实例化");
	}

	public void say(){
		System.out.println("hello world");
	}

	@Override
	public String toString() {
		return "InstanceB";
	}

}
