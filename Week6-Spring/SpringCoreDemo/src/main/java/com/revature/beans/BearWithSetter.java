package com.revature.beans;

public class BearWithSetter extends Bear {
	
	//uses superclass setter
	
	@Override
	public String toString() {
		return "BearWithSetter [id=" + id + ", name=" + name + ", cave=" + cave + "]";
	}
	
	@Override
	public void methodInBear() {
		System.out.println("Method in BearWithSetter. This bear is "+toString());
	}

}
