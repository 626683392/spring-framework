package test.mytest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("test.mytest")
public class TestApplicationContext {


	public static void main(String[] args) {

//		DefaultListableBeanFactory context = new DefaultListableBeanFactory();
//
//
//		RootBeanDefinition bd = new RootBeanDefinition(MyBeanDemo.class);
////		context.registerBeanDefinition("demo",bd);
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/WEB-INF/applicationcontext.xml");
//
//
//		MyBeanDemo demo = (MyBeanDemo) context.getBean("demo");
//		System.out.println(demo);

		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestApplicationContext.class);
		MyBeanDemo demo = (MyBeanDemo) applicationContext.getBean("demo");
		System.out.println(demo);


	}

}
