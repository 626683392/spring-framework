package org.springframework.mytests.bean;

public class MyBeanDemo {
    private String beanName = "bean";

    private HelloBean helloBean;

	public HelloBean getHelloBean() {
		return helloBean;
	}

	public void setHelloBean(HelloBean helloBean) {
		this.helloBean = helloBean;
	}

	public String getBeanName() {
        return beanName;
    }
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

	@Override
	public String toString() {
		return "MyBeanDemo{" +
				"beanName='" + beanName + '\'' +
				", helloBean=" + helloBean.getName() +
				'}';
	}
}