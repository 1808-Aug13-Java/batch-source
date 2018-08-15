package com.revature.fourPillars;

public abstract class Mammal extends Animal {

	protected int numberOfLegs;
	
	public abstract void shed();
	
	public void setNumberOfLegs(int l) {
		numberOfLegs = l;
	}
	
	public int getNumberOfLegs() {
		return numberOfLegs;
	}
	
	public String genus() {
		return "Mammal";
	}
}
