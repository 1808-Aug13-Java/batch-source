package com.revature.beans;

public class BearWithSetter extends Bear{

	@Override
	public String toString() {
		return "BearWithSetter [id=" + id + ", name=" + name + ", cave=" + cave + "]";
	}
	
	
	@Override
	public void methodInBear() {
		System.out.println("this method has been overwritten with the setter"+ toString());
	}

}
