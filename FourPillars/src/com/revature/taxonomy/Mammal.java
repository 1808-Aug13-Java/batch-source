package com.revature.taxonomy;
import com.revature.exceptions.TooManyLegsException;

//Subclass Mammal inherits from superclass Animal
public class Mammal extends Animal {
	
	enum Species {DOG, CAT, MOUSE};
	
	public Mammal() {
		super();
		vertabrate = true;
	}
	
	private boolean fur;
	
	public boolean hasFur() {
		return fur;
	}
	
	public void isSleeping() {
		System.out.println("False");
	}
	
}
