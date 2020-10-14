package com.meetkiki.customerimportselector;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MethodCostTimeImportSelector implements DeferredImportSelector {


//	@Override
//	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
//										BeanDefinitionRegistry registry) {
//		RootBeanDefinition beanDefinition = new RootBeanDefinition(MethodCostTimeImportSelector.class);
//		registry.registerBeanDefinition("methodCostTimeProxyBeanPostProcessor", beanDefinition);
//	}

	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[]{MethodCostTimeProxyBeanPostProcessor.class.getName()};
	}

}