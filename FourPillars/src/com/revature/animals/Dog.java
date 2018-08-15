package com.revature.animals;

//The extends keyword denotes that we are practicing Inheritance by subclassing/extending "Animals"
public class Dog extends Animals {
	
	// These properties are securely encapsulated through the use of the private access modifier
	// This is an example of the Encapsulation pillar, as we are effectively hiding/securing the
	// data until it is accessed via getter/setter methods
	private int numberOfLegs;
	private String favoriteToy;
	private String breed;
	
	public Dog() {
		super();
	}
	
	public Dog(boolean hasLegs, String vocalizationType, String movementStyle) {
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

	public void setFavoriteToy(String favoriteToy) {
		this.favoriteToy = favoriteToy;
	}
	
	public String getFavoriteToy() {
		return favoriteToy;
	}
	
	public void setBreed(String breed) {
		this.breed = breed;
	}
	
	public String getBreed() {
		return breed;
	}
	
	// Note the use of polymorphism via overriding the toString method
	// Here, the toString() method is overriden so that we can return a String representation
	// of the information below
	@Override
	public String toString() {
		return "Dog [numberOfLegs=" + numberOfLegs + ", favoriteToy=" + favoriteToy + ", breed=" + breed + "]";
	}
	
}
