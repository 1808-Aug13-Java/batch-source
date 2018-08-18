package com.revature.henry;

public class Tarantula extends Animal {
	// Tarantula inherits traits from the animal class
	public int numlegs = 8;
	public boolean IsPoisonous = true;
	
	public Tarantula(String name, String diet, int mass) throws AnimalMassException {
		super(name, diet, mass);
		if (mass<0) {
			throw new AnimalMassException();
		}
		if (mass > 1000000000) {
			throw new MassTooLargeException("Mass cannot be that large");
		}
	}

	@Override
	public String toString() {
		return ("The number of legs: " + numlegs +"\n"
				+ "This animal is poisonous: " + IsPoisonous + "\n");
	}

	
}
