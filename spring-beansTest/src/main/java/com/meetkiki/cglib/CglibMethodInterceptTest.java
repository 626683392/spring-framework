package com.meetkiki.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CglibMethodInterceptTest {

	public static void main(String[] args) {
//		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "./");

		//创建一个Enhancer对象
		Enhancer enchaner = new Enhancer();
		//设置被代理的类
		enchaner.setSuperclass(Student.class);

		Student originStudent = new Student();
		//创建一个回调接口
		Callback interceptor = new CustomerInterceptor(originStudent);

		enchaner.setCallback(interceptor);
		Student student = (Student) enchaner.create();
		student.getStuName();
	}
}