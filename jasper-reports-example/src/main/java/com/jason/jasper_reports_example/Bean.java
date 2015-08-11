package com.jason.jasper_reports_example;

import java.util.Date;

public class Bean {
	String id = "123";
	String first_name = "smegoff";
	String last_name = "smeg";
	Date date = new Date(System.currentTimeMillis());
	public String getId() {
		return id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public Date getDate() {
		return date;
	}
	

}