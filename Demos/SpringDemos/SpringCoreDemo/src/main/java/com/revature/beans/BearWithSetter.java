package com.revature.beans;

public class BearWithSetter extends Bear {
	
	// uses superclass's setter
	
	@Override
	public String toString() {
		return "BearWithSetter [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	@Override
	public void methodInBear() {
		System.out.println("Method in Bear with Setter. the bear is " + toString());
	}
}
