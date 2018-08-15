package com.revature.animals;

// Inheritance
// Dog inherits from AnimalBase parent class
public class Dog extends AnimalBase{

	// Encapsulation
	// Private access modifier encapsulate Dog's member variables
	private String breed;
	private boolean isVaccinated;
	private boolean isGoodBoy;
	
	// Default constructor
	public Dog() {
		super();
	}

	// Polymorphism
	// Overloads default constructor
	public Dog(int numApendages, int landSpeed, boolean hasTail) {
		super(numApendages, landSpeed, hasTail);
	}

	// Getters and Setters
	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public boolean isVaccinated() {
		return isVaccinated;
	}

	public void setVaccinated(boolean isVaccinated) {
		this.isVaccinated = isVaccinated;
	}

	public boolean isGoodBoy() {
		return isGoodBoy;
	}

	public void setGoodBoy(boolean isGoodBoy) {
		this.isGoodBoy = isGoodBoy;
	}

	// Polymorphism
	// Overrides parent's toString
	@Override
	public String toString() {
		return "Dog [breed=" + breed + ", isVaccinated=" + isVaccinated + ", isGoodBoy=" + isGoodBoy + "]";
	}
	
	
}
