package org.springframework.mytests.bean;

public class HelloBean {

	private String name;
	private String massage;
	private MyBeanDemo myBeanDemo;

	public HelloBean(String name, String massage) {
		this.name = name;
		this.massage = massage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMassage() {
		return massage;
	}

	public void setMassage(String massage) {
		this.massage = massage;
	}

	public MyBeanDemo getMyBeanDemo() {
		return myBeanDemo;
	}

	public void setMyBeanDemo(MyBeanDemo myBeanDemo) {
		this.myBeanDemo = myBeanDemo;
	}

	@Override
	public String toString() {
		return "HelloBean{" +
				"name='" + name + '\'' +
				", massage='" + massage + '\'' +
				", myBeanDemo=" + myBeanDemo +
				'}';
	}
}
