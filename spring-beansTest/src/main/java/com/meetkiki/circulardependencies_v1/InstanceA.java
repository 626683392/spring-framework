package com.meetkiki.circulardependencies_v1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class InstanceA  {

	@Autowired
    private InstanceB instanceB;

    public InstanceA() {
        System.out.println("InstanceA实例化");
    }


	@Override
	public String toString() {
		return "InstanceA";
	}
}
