package com.meetkiki.circulardependencies_v4;

public class JdkDynamicAopProxy {

	public InstanceA getProxy() {
		return new InstanceA() {
			@Override
			public void say() {
				System.out.println("代理say开始");
				super.say();
				System.out.println("代理say结束");
			}
		};
	}


}
