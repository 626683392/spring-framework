package com.meetkiki.customerimportselector;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MethodCostTimeImportSelector implements ImportBeanDefinitionRegistrar {


	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
										BeanDefinitionRegistry registry) {
		RootBeanDefinition beanDefinition = new RootBeanDefinition(MethodCostTimeImportSelector.class);
		registry.registerBeanDefinition("methodCostTimeProxyBeanPostProcessor", beanDefinition);
	}

	// @Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[]{MethodCostTimeProxyBeanPostProcessor.class.getName()};
	}

}