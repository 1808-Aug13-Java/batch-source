package com.revature.fourpillars;

public class Animal {
	private boolean isAPet;
	private int numberOfLegs;
	
	public int getNumberOfLegs() {
		return numberOfLegs;
	}
	
	public boolean getIsAPet() {
		return isAPet;
	}
	
	
	public void setNumberOfLegs(int numberOfLegs) throws NegativeNumberOfLegsException {
		if(0<numberOfLegs) {
			this.numberOfLegs=numberOfLegs;
		}
		else {
			throw new NegativeNumberOfLegsException(
					"Animals can't have a negative number of legs...");
		}
		
	}
	
	public void setisAPet() {
		isAPet = true;
	}
	
	public void speak() {
		System.out.println("An Animal Noise!");
	}


	
}
