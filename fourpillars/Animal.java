package com.revature.fourpillars;

public class Animal {
	
	//encapsulation due to private declarations
	private boolean isAPet;
	private int numberOfLegs;
	private double growthRate = 1.0;
	
	public Animal() {
		super();
	}
	public int getNumberOfLegs() {
		return numberOfLegs;
	}
	
	public void grow(double growthRate2) {
		this.growthRate = growthRate;
	}
	
	protected Animal(boolean isAPet, int numberOfLegs, double growthRate) {
		super();
		this.isAPet = isAPet;
		this.numberOfLegs = numberOfLegs;
		this.growthRate = growthRate;
	}
	public boolean getIsAPet() {
		return isAPet;
	}
	
	//unchecked exception conditions: if negative number of legs is passed, an error is outputted
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
