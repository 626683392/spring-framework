package org.springframework.mytests.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class TestDemo {
    public static void main(String[] args) {
        BeanFactory factory = new XmlBeanFactory(new ClassPathResource("spring-bean.xml"));

        try {
			HelloBean helloBean = (HelloBean)factory.getBean("helloBean");
			System.out.println(helloBean);


			MyBeanDemo bean = (MyBeanDemo)factory.getBean("demo");
			System.out.println(bean);
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
}