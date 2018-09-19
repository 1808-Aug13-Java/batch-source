package com.revature.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Bear {

	private int id;
	
	@Value("Harold")
	private String name;
	
	@Autowired
	private Cave cave;
	
	public Bear() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Bear(int id, String name, Cave cave) {
		super();
		this.id = id;
		this.name = name;
		this.cave = cave;
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
	public Cave getCave() {
		return cave;
	}
	public void setCave(Cave cave) {
		this.cave = cave;
	}

	@Override
	public String toString() {
		return "Bear [id=" + id + ", name=" + name + ", cave=" + cave + "]";
	}

	
	
	
}
