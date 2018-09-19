package com.revature.beans;

public class BearWithConstructor extends Bear {

	public BearWithConstructor(Cave cave) {
		super();
		this.cave = cave;
	}

	@Override
	public String toString() {
		return "BearWithConstructor [id=" + id + ", name=" + name + ", cave=" + cave + "]";
	}
	
	@Override
	public void methodInBear() {
		System.out.println("method in BearWithConstructor. the bear is: "+toString());
	}
	
	
	
	

}
