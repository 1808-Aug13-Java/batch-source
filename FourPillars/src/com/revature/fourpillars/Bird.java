package com.revature.fourpillars;

public class Bird extends Animal{
	String species;

	public Bird() {}
	public Bird(int numLeg, String sex, String spec) //example of inheritance. numlegs and sex from Animal
	{
		this.numLegs = numLeg;
		this.sex = sex;
		this.species = spec;
	}	//constructor
	
	@Override							//Example of Polymorphism because we're overriding the
	public void displayInfo()		//abstract method from the superclass Animal and filling out a 
	{								//implementation specific to Bird class
		System.out.println("Sex, number of legs, and species: " + sex+", "+numLegs+", "+species);
	}
	
	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}
	
	
}
