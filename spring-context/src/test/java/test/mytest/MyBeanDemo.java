package test.mytest;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyBeanDemo {
    private String beanName = "bean";

    private Date dateValue;

	public Date getDateValue() {
		return dateValue;
	}

	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
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
				", dateValue=" + dateValue +
				'}';
	}
}