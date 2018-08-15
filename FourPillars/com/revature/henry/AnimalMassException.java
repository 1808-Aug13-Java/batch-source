package com.revature.henry;

/*
 * This class extends Exception to handle error when the mass of an animal is negative
 * Since an animal's mass cannot be negative, this custom error will appear.
 */
public class AnimalMassException extends Exception {
	
	public AnimalMassException() {
		super("Animal mass cannot be negative.");
	}

}
