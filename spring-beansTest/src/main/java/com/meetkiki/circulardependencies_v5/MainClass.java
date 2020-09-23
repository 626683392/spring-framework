package com.meetkiki.circulardependencies_v5;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

import java.lang.reflect.Field;
import java.util.*;

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

	// 三级缓存 完全为了解耦
	private static final Map<String, ObjectFactory> singletonFactories = new HashMap<>();

	// 标记正在创建的bean
	private static final Set<String> singletonsCurrentlyInCreation = new HashSet<>();


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
		Object bean = createBean(beanName);

		// 成熟的bean添加到一级缓存
		singletonObjects.put(beanName, bean);
		return bean;
	}

	private static Object createBean(String beanName) throws IllegalAccessException {

		singletonsCurrentlyInCreation.add(beanName);
		// 实例化
		RootBeanDefinition beanDefinition = (RootBeanDefinition) BEAN_DEFINITION_MAP.get(beanName);
		Object instanceBean = BeanUtils.instantiateClass(beanDefinition.getBeanClass());
		// 只在循环依赖的情况下才在实例化后创建Proxy 正常的bean还是在初始化后创建
		singletonFactories.put(beanName, () -> new JdkProxyBeanPostProcessor().getEarlyBeanReference(instanceBean, beanName));
		// earlySingletonObjects.put(beanName, bean);

		// 属性赋值
		Field[] fields = instanceBean.getClass().getDeclaredFields();
		for (Field field : fields) {
			Autowired autowired = field.getAnnotation(Autowired.class);
			// 说明有其他属性
			if (Objects.nonNull(autowired)) {
				field.setAccessible(true);
				String name = field.getName();
				Object filedObj = getBean(name);
				field.set(instanceBean, filedObj);
			}
		}
		// 初始化 init -method 等等

		// 正常情況下 在初始化之后创建

		singletonsCurrentlyInCreation.remove(beanName);

		// 递归完成后A还是原实例 所以要替换为代理对象 如果二级缓存有 从二级缓存中去取
		if (earlySingletonObjects.containsKey(beanName)) {
			return earlySingletonObjects.get(beanName);
		}

		return instanceBean;
	}


	public static Object getSingleton(String beanName) {
		// 先从一级缓存中拿
		Object bean = singletonObjects.get(beanName);
		// 这里说明是循环依赖
		if (bean == null && singletonsCurrentlyInCreation.contains(beanName)) {
			// 二级缓存中拿
			bean = earlySingletonObjects.get(beanName);
			if (bean == null) {
				ObjectFactory factory = singletonFactories.get(beanName);
				// 三级缓存中直接创建
				bean = factory.getObject();
				// 放入二级缓存中
				earlySingletonObjects.put(beanName, bean);

				return bean;
			}
		}
		return bean;
	}

}
