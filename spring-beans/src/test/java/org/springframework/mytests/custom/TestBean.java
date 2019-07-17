package org.springframework.mytests.custom;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class TestBean {


	public static void main(String[] args) {
		BeanFactory factory = new XmlBeanFactory(new ClassPathResource("myname_test.xml"));

		try {
			User bean = (User)factory.getBean("testbean");
			System.out.println(bean);


		} catch (BeansException e) {
			e.printStackTrace();
		}
	}
}
