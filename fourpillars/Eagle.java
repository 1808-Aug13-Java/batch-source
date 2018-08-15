package com.revature.fourpillars;
//inheritance because of extends keyword and the parent/child relationship 
public class Eagle extends Animal {
	
	private double growthRate = 3.0;

	public void speak() {
		System.out.println("CAWWWWWW!");
	}
	
	public void grow() {
		
		System.out.println(growthRate);
	}

	/**
	 * @param growthRate
	 */
	public Eagle(double growthRate) {
		super();
		
	}

	public Eagle() {
		// TODO Auto-generated constructor stub
	}
	
	
}
