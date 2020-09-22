package com.meetkiki.cglib;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

public class CglibMethodInterceptTest {

	public static void main(String[] args) {
		//创建一个Enhancer对象
		Enhancer enchaner = new Enhancer();
		//设置被代理的类
		enchaner.setSuperclass(Student.class);
		//创建一个回调接口
		Callback interceptor = new CustomerInterceptor();
		enchaner.setCallback(interceptor);
		Student student = (Student) enchaner.create();
		student.getStuName();
	}
}