package com.revature.animals;

// Inheritance & Abstraction
// Fish inherits from AnimalBase and implements AquaticInterface
public class Fish extends AnimalBase implements AquaticInterface {

	// Encapsulation
	// Private access modifier to encapsulate member variables
	private String color;
	private int numFins;
	private int currDepth;
	
	// Default Constructor
	public Fish() {
		super();
	}

	// Overload constructor
	public Fish(int numApendages, int landSpeed, boolean hasTail) {
		super(numApendages, landSpeed, hasTail);
	}

	// Getters and Setters
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getNumFins() {
		return numFins;
	}

	public void setNumFins(int numFins) {
		this.numFins = numFins;
	}

	public int getCurrDepth() {
		return currDepth;
	}

	public void setCurrDepth(int currDepth) {
		this.currDepth = currDepth;
	}

	// Defining methods provided by AquaticInterface
	public void swim() {
		System.out.println("Fish swims through the water");
	}

	// Override default dive() from AquaticInterface
	@Override
	public void dive() {
		this.currDepth++;
	}
}
