package com.revature.game;

public class Associate {
	
	private String name;

	public Associate() {
		super();
	}

	public Associate(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Associate [name=" + name + "]";
	}

}
