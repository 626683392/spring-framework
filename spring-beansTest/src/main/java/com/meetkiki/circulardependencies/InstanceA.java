package com.meetkiki.circulardependencies;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class InstanceA  {

	@Autowired
    private InstanceB instanceB;

    public InstanceA() {
        System.out.println("InstanceA实例化");
    }

    public void say(){
		System.out.println("hello world");
	}

	@Override
	public String toString() {
		return "InstanceA";
	}
}
