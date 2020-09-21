package com.meetkiki.circulardependencies;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class InstanceA  {

	@Autowired
    private InstanceB instanceB;

    @PostConstruct
    public void init() {
		instanceB.setInstanceA(this);
	}

    public InstanceB getInstanceB() {
        return instanceB;
    }

    public void setInstanceB(InstanceB instanceB) {
        this.instanceB = instanceB;
    }

    public InstanceA(InstanceB instanceB) {
        this.instanceB = instanceB;
    }


    public InstanceA() {
        System.out.println("InstanceA实例化");
    }


	@Override
	public String toString() {
		return "InstanceA";
	}
}
