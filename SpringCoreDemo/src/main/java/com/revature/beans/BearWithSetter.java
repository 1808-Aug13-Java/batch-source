package com.revature.beans;

public class BearWithSetter extends Bear {

	
	@Override
	public String toString() {
		return "BearWithSetter [id=" + id + ", name=" + name + ", cave=" + cave + "]";
	}
	
	@Override
	public void methodInBear() {
		System.out.println("method in BearWithSetter. the bear is: "+toString());
	}
}
