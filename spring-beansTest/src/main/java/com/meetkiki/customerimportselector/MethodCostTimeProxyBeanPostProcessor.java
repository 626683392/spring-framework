package com.meetkiki.customerimportselector;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MethodCostTimeProxyBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (beanName.startsWith("service")) {
			// 创建动态代理类
			return CostTimeProxy.createProxy(bean);
		}
		return bean;
	}

}