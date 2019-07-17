package org.springframework.mytests.custom;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class MyNameSpaceHander extends NamespaceHandlerSupport {

	/**
	 * 自定义实现NamespaceHandlerSupport 目的是把user标签解析器注册到spring容器中
	 */
	@Override
	public void init() {
		registerBeanDefinitionParser("user",new UserBeanDefinitionParser());
	}
}
