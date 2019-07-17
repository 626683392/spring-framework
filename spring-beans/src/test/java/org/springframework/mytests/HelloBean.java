package org.springframework.mytests;

public class HelloBean {

	private String name;
	private String massage;

	public HelloBean(String name, String massage) {
		this.name = name;
		this.massage = massage;
	}

	@Override
	public String toString() {
		return "HelloBean{" +
				"name='" + name + '\'' +
				", massage='" + massage + '\'' +
				'}';
	}
}
