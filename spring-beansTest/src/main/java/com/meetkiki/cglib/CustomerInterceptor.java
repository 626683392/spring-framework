package com.meetkiki.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CustomerInterceptor implements MethodInterceptor {
	/**
	 * All generated proxied methods call this method instead of the original method.
	 * The original method may either be invoked by normal reflection using the Method object,
	 * or by using the MethodProxy (faster).
	 *
	 * @param obj    "this", the enhanced object cglib生成的代理对象
	 * @param method intercepted Method 	被代理对象方法
	 * @param args   argument array; primitive types are wrapped	方法入参
	 * @param proxy  used to invoke super (non-intercepted method); may be called	 代理方法
	 *               as many times as needed
	 * @return any value compatible with the signature of the proxied method. Method returning void will ignore this value.
	 * @throws Throwable any exception may be thrown; if so, super method will not be invoked
	 * @see MethodProxy
	 */
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.err.println("原方法名是 ： " + method.getName());
		System.err.println("原方法声明的类为 " + method.getDeclaringClass());
		//System.err.println("我是 " + proxy.invokeSuper(obj, args));
		System.err.println("代理方法调用结束了");
		Object result = proxy.invokeSuper(obj, args);
//		 Object result = proxy.invoke(target, args);
		return result;
	}


	private Object target;

	public CustomerInterceptor(Object target) {
		this.target = target;
	}
}
