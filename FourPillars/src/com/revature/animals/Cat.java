package com.revature.animals;

//The extends keyword denotes that we are practicing Inheritance by subclassing/extending "Animals"
public class Cat extends Animals {

	// These properties are securely encapsulated through the use of the private access modifier
	// This is an example of the Encapsulation pillar, as we are effectively hiding/securing the
	// data until it is accessed via getter/setter methods
	private int numberOfLegs;
	private String colorOfFur;
	private boolean likesDogs;
	
	public Cat() {
		super();
	}
	
	public Cat(boolean hasLegs, String vocalizationType, String movementStyle) {
		super(hasLegs, vocalizationType, movementStyle);
	}
	
	// all setXY below are setter methods
	// all getXY below are getter methods
	public void setNumberOfLegs(int numberOfLegs) {
		this.numberOfLegs = numberOfLegs;
	}
	
	public int getNumberOfLegs() {
		return numberOfLegs;
	}

	public void setColorOfFur(String colorOfFur) {
		this.colorOfFur = colorOfFur;
	}
	
	public String getColorOfFur() {
		return colorOfFur;
	}
	
	public void setLikesDogs(boolean likesDogs) {
		this.likesDogs = likesDogs;
	}
	
	public boolean getLikesDogs() {
		return likesDogs;
	}
	
	// Note the use of polymorphism via overriding the toString method
	// Here, the toString() method is overriden so that we can return a String representation
	// of the information below
	@Override
	public String toString() {
		return "Cat [numberOfLegs=" + numberOfLegs + ", colorOfFur=" + colorOfFur + ", likesDogs=" + likesDogs + "]";
	}
	
	
	
	
	
	

}
