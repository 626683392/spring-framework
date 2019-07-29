package test.mytest;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePropertyEditor extends PropertyEditorSupport {

	private String format = "yyyy-MM-dd";

	public void setFormat(String format) {
		this.format = format;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		System.out.println("text - " +  text);
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);

		try {
			// 处理设置时间的逻辑
			Date date = dateFormat.parse(text);
			this.setValue(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
