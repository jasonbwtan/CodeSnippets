package com.jason.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Class representing an option to vote for in a poll
 * @author Jason
 *
 */
@Entity
public class Option {
	
	@Id
	@GeneratedValue
	@Column(name="OPTION_ID")
	private long id;
	
	@Column(name="OPTION_VALUE")
	private String value;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
