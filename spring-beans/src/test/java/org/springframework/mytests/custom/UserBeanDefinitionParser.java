package org.springframework.mytests.custom;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {


	@Override
	protected Class<?> getBeanClass(Element element) {
		return User.class;
	}

	/**
	 * 复写解析xml的方法
	 * @param element the XML element being parsed
	 * @param builder used to define the {@code BeanDefinition}
	 */
	@Override
	protected void doParse(Element element, BeanDefinitionBuilder builder) {
		String userName = element.getAttribute("userName");
		String email = element.getAttribute("email");
		// 将解析的数据放入到BeanDefinitionBuilder中
		if (StringUtils.hasText(userName)){
			builder.addPropertyValue("userName",userName);
		}
		if (StringUtils.hasText(email)){
			builder.addPropertyValue("email",email);
		}
	}
}
