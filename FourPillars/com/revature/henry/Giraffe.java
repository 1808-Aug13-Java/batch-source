package com.revature.henry;

public class Giraffe extends Animal {
	//giraffe inherits from the animal class
	public String color = "Orange";
	public String location = "Africa";
	
	public Giraffe(String name, String diet, int mass) throws AnimalMassException {
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
		return ("This animal's color: " + color +"\n"
				+ "This animal is found in: " + location + "\n");
	}

	
}
