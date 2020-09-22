package com.meetkiki.circulardependencies_v1;

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

	private static void loadBeanDefinition(){
		RootBeanDefinition instanceA = new RootBeanDefinition(InstanceA.class);
		RootBeanDefinition instanceB = new RootBeanDefinition(InstanceB.class);
		BEAN_DEFINITION_MAP.put("instanceA",instanceA);
		BEAN_DEFINITION_MAP.put("instanceB",instanceB);
	}

	// 一级缓存
	private static final Map<String,Object> singletonObjects = new HashMap<>();


	public static void main(String[] args) throws IllegalAccessException {
		loadBeanDefinition();

		// 循环创建bean
		for (String beanName : BEAN_DEFINITION_MAP.keySet()) {
			Object bean = getBean(beanName);
			System.out.println(bean);
		}
	}

	private static Object getBean(String beanName) throws IllegalAccessException {
		// 实例化
		RootBeanDefinition beanDefinition = (RootBeanDefinition) BEAN_DEFINITION_MAP.get(beanName);
		Object bean = BeanUtils.instantiateClass(beanDefinition.getBeanClass());
		// 属性赋值
		Field[] fields = bean.getClass().getDeclaredFields();
		for (Field field : fields) {
			Autowired autowired = field.getAnnotation(Autowired.class);
			// 说明有其他属性
			if (Objects.nonNull(autowired)){
				field.setAccessible(true);
				String name = field.getName();
				Object filedObj = getBean(name);
				field.set(bean,filedObj);
			}

		}

		// 初始化 init -method

		// 添加到一级缓存
		singletonObjects.put(beanName,bean);

		return bean;
	}
}
