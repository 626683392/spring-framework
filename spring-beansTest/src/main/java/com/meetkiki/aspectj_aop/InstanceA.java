package com.meetkiki.aspectj_aop;


import org.springframework.stereotype.Component;


@Component
public class InstanceA {
	public InstanceA() {
		/**
		 * 主题：Spring 5.x从Bean加载到AOP原理浅析
		 * 简介：从写一个注解注册Bean到项目启动都经历了什么？
		 * 循环依赖是什么？先有鸡还是现有蛋？
		 * Spring如何解决Bean与Bean的循环依赖？
		 * 为什么AOP在同一个类中调用切面会失效？Transactional注解有毒？
		 * 为什么@Configuration在同个类中调用其他方法切面又生效了？
		 *
		 * 对于Spring实现机制有兴趣的同学可以来探讨。
		 */
		System.out.println("InstanceA实例化");
	}

	public void say() {
		say2();
	}

	@LockAction
	public void say2() {
		System.out.println("hello world");
	}

}
