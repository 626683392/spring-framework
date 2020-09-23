package com.meetkiki.circulardependencies_v3;

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

	private static Object getBean(String beanName) throws IllegalAccessException {
		Object singleton = getSingleton(beanName);
		if (singleton != null) {
			return singleton;
		}

		// 实例化
		RootBeanDefinition beanDefinition = (RootBeanDefinition) BEAN_DEFINITION_MAP.get(beanName);
		Object bean = BeanUtils.instantiateClass(beanDefinition.getBeanClass());

		// 已经实例化 缓存
		earlySingletonObjects.put(beanName,bean);
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

		// 初始化 init -method

		// 添加到一级缓存
		singletonObjects.put(beanName, bean);
		return bean;
	}


	public static Object getSingleton(String beanName) {
		Object obj = singletonObjects.getOrDefault(beanName, null);
		if (obj != null){
			return obj;
		}
		return earlySingletonObjects.getOrDefault(beanName,null);
	}

}
