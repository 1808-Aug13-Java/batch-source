package com.revature.beans;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class Bear {
	
	private int id;
	private String name;
	private Date birthday;
	
	public Bear() {
		super();
	}

	public Bear(int id, String name, Date birthday) {
		super();
		this.id = id;
		this.name = name;
		this.birthday = birthday;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "Bear [id=" + id + ", name=" + name + ", birthday=" + birthday + "]";
	}

}
