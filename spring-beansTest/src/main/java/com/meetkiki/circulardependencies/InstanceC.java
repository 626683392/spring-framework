package com.meetkiki.circulardependencies;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class InstanceC {

	@Autowired
	private InstanceC instanceC;

	public InstanceC() {
		System.out.println("InstanceC实例化");
	}

	public void say() {
		System.out.println("hello world " + instanceC.toString());
	}

	@Override
	public String toString() {
		return "InstanceC";
	}
}
