package com.meetkiki.circulardependencies_v4;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainClass {

	private static final Map<String, BeanDefinition> BEAN_DEFINITION_MAP = new HashMap<>();

	private static void loadBeanDefinition() {
		RootBeanDefinition instanceA = new RootBeanDefinition(InstanceA.class);
		RootBeanDefinition instanceB = new RootBeanDefinition(InstanceB.class);
		BEAN_DEFINITION_MAP.put("instanceA", instanceA);
		BEAN_DEFINITION_MAP.put("instanceB", instanceB);
	}

	// 一级缓存
	private static final Map<String, Object> singletonObjects = new HashMap<>();

	// 二级缓存 主要是为了将成熟（完整的bean）和非成熟的bean 分离
	private static final Map<String, Object> earlySingletonObjects = new HashMap<>();


	public static void main(String[] args) throws IllegalAccessException {
		loadBeanDefinition();

		InstanceA instanceA = (InstanceA) getBean("instanceA");
		InstanceB instanceB = (InstanceB) getBean("instanceB");

		instanceA.say();
		instanceB.say();
	}

	// 假设 A 使用了AOP，@Pointcut("execution(* *..InstanceA.*(..))") //要给A创建动态代理
	private static Object getBean(String beanName) throws IllegalAccessException {
		Object singleton = getSingleton(beanName);
		if (singleton != null) {
			return singleton;
		}

		// 实例化
		RootBeanDefinition beanDefinition = (RootBeanDefinition) BEAN_DEFINITION_MAP.get(beanName);
		Object bean = BeanUtils.instantiateClass(beanDefinition.getBeanClass());
		// 只在循环依赖的情况下才在实例化后创建Proxy 正常的bean还是在初始化后创建

		// 假设有beanA 动态代理 创建Proxy（非耦合 beanPostProcessor)
		// Object instance = new JdkProxyBeanPostProcessor().getEarlyBeanReference(bean, beanName);
		earlySingletonObjects.put(beanName, bean);

		// 属性赋值
		Field[] fields = bean.getClass().getDeclaredFields();
		for (Field field : fields) {
			Autowired autowired = field.getAnnotation(Autowired.class);
			// 说明有其他属性
			if (Objects.nonNull(autowired)) {
				field.setAccessible(true);
				String name = field.getName();
				Object filedObj = getBean(name);
				field.set(bean, filedObj);
			}

		}

		// 初始化 init -method 等等

		// 正常情況下 在初始化之后创建

		// 成熟的bean添加到一级缓存
		singletonObjects.put(beanName, bean);
		return bean;
	}


	public static Object getSingleton(String beanName) {
		// 先从一级缓存中拿
		Object obj = singletonObjects.getOrDefault(beanName, null);
		if (obj != null) {
			return obj;
		}
		// 在从二级缓存中拿
		if (earlySingletonObjects.containsKey(beanName)) {
			// 来到这里说明有循环依赖
			// 假设有beanA 动态代理 创建Proxy（非耦合 beanPostProcessor) 开始创建动态代理
			Object instance = new JdkProxyBeanPostProcessor().getEarlyBeanReference(earlySingletonObjects.get(beanName), beanName);
			// 放入二级缓存 避免 多重循环依赖重复创建bean
			earlySingletonObjects.put(beanName, instance);
			return instance;
		}

		return null;
	}

}
