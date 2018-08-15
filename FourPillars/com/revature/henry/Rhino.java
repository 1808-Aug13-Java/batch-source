package com.revature.henry;

public class Rhino extends Animal {
	//rhino inherits from the animal class
	public boolean hasHorn = true;
	public int numSpecies = 5;
	
	public Rhino(String name, String diet, int mass) throws AnimalMassException {
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
		return ("This animal has a horn: " + hasHorn+"\n"
				+ "Number of species: " + numSpecies + "\n");
	}

	
}
