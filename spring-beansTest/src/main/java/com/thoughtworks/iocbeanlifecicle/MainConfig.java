package com.thoughtworks.iocbeanlifecicle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = {"com.thoughtworks.iocbeanlifecicle"})
public class MainConfig {

	@Bean(initMethod = "initPerson")
	public Person person() {
		Person person = new Person();
		person.setName("张三");
		person.setSex("男");
		return person;
	}
}
