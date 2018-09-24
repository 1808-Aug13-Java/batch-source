package com.revature.beans;

import java.sql.Date;

import org.springframework.stereotype.Component;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Bear {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)  
	private Integer id;
	private String name;
	private Date birthday;
	
	public Bear() {
	}

	public Bear(String name, Date birthday) {
		super();
		this.name = name;
		this.birthday = birthday;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
