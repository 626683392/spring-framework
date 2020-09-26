package com.meetkiki.configurationtest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@Component
public class MainConfig {

	@Bean
	public InstanceA instanceA() {
		return new InstanceA();
	}


	@Bean
	public InstanceB instanceB() {
		InstanceB instanceB = new InstanceB();
		instanceB.setInstanceA(instanceA());
		return instanceB;
	}
}