package com.meetkiki.customerimportselector;


import org.springframework.stereotype.Service;


@Service
public class ServiceA {

	public ServiceA() {
		System.out.println("ServiceA实例化");
	}

	public void say() {
		System.out.println("hello world");
	}

	@Override
	public String toString() {
		return "InstanceA";
	}
}
