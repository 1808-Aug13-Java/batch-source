package com.revature.beans;

public class BearWithConstructor extends Bear{
	
	//use constructor injection to autowire our cave 

	public BearWithConstructor (Cave cave) {
		super();
		this.cave=cave;
	}
	@Override
	public void setCave(Cave cave) {
		this.cave = cave;
	}

	@Override
	public String toString() {
		return "BearWithConstructor [id=" + id + ", name=" + name + ", cave=" + cave + "]";
	}
	@Override
	public void methodInBear() {
		System.out.println("this method has been overwritten"+ toString());
	}
	
}
