package com.revature.henry;

public class Duck extends Animal {
	
	public boolean CanFly = true;
	public boolean CanSwim = true;
	
	public Duck(String name, String diet, int mass) throws AnimalMassException {
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
		return ("This animal can fly: " + CanFly +"\n"
				+ "This animal can swim: " + CanSwim + "\n");
	}

	
}
