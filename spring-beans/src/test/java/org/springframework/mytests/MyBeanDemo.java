package org.springframework.mytests;

public class MyBeanDemo {
    private String beanName = "bean";

    public String getBeanName() {
        return beanName;
    }
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}