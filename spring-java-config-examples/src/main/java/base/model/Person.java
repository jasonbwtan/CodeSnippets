package base.model;

import org.springframework.beans.factory.annotation.Autowired;

public class Person {
	public String name;
	public String email;
	
	@Autowired
	public Book book;
	
	public void setName(String string) {
		this.name = string;
	}

	public void setEmail(String string) {
		this.email = string;
	}

}
