package com.meetkiki.parsebeandefinition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {CompentC.class, TestImportSelect.class, TestImportBeanDefinitionRegister.class})
@ComponentScan(basePackages = {"com.meetkiki.parsebeandefinition"})
public class MainConfig {

	@Bean
	public CompentD compentD() {
		return new CompentD();
	}

	@Bean
	public CompentC compentC() {
		return new CompentC();
	}
}
