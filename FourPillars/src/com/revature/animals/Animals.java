package com.revature.animals;

public class Animals {
	
	// we set these variables to "protected" so that we can access them anywhere within the package
	protected boolean hasLegs;
	protected String vocalizationType;
	protected String movementStyle;
	
	// no arguments constructor
	public Animals() {
		super();
	}
	
	public Animals(boolean hasLegs, String vocalizationType, String movementStyle) {
		this.hasLegs = hasLegs;
		this.vocalizationType = vocalizationType;
		this.movementStyle = movementStyle;
	}
	
	// all setXY below are setter methods
	// all getXY below are getter methods
	// Create a public setter method so that we can access the Animals properties in subclasses
	public void setHasLegs(boolean hasLegs) {
		this.hasLegs = hasLegs;
	}
	
	// return the value in a public getter method for indirect access of value in "Pets" class
	public boolean getHasLegs() {
		return hasLegs;
	}
	
	public void setVocalizationType(String vocalizationType) {
		this.vocalizationType = vocalizationType;
	}
	
	public String getVocalizationType() {
		return vocalizationType;
	}
	
	public void setMovementStyle(String movementStyle) {
		this.movementStyle = movementStyle;
	}
	
	public String getMovementStyle() {
		return movementStyle;
	}
	
	// Note the use of polymorphism via overriding the toString method
	// Here, the toString() method is overriden so that we can return a String representation
	// of the information below
	@Override
	public String toString() {
		return "Animals [hasLegs=" + hasLegs + ", vocalizationType=" + vocalizationType + ", movementStyle="
				+ movementStyle + "]";
	}

}
