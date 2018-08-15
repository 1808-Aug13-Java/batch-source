package com.revature.animals;

import com.revature.exceptions.NonbinaryGenderException;

public class Lion extends Animal { // Inherits from Animal class (Pillar of OOP 1)
	
	private boolean hasMane;
	
	public Lion() {
		super(); // calls superclass constructor
	}
	
	// constructor overloading
	public Lion(String gender) {
		super(); // calls superclass constructor
		this.numberOfLegs = 4;
		this.weight = 600; // not necessarily an accurate lion weight
		this.animalNoise = "Roar!";
		this.isDomesticated = false;

		if (gender == "male" || gender == "female") { // checks if gender is binary
			this.gender = gender;
			switch (gender) {
				case "male":
					this.hasMane = true;
					break;
				case "female":
					this.hasMane = false;
			}
		} else {
			try {
				throw new NonbinaryGenderException(); // checked exception
			} catch (NonbinaryGenderException e) {
				System.out.println("Exception handled.");
			}
		}
	}
	
	// these methods override the default implementation in the Animal class, 
	// demonstrating Polymorphism (Pillar of OOP 2)
	@Override
	public void walk() {
		if (this.hasMane) {
			System.out.println("Lion is proudly strutting, mane flowing.");
		} else {
			System.out.println("Lioness is stalking her prey.");
		}
	}
	
	// implementation for the abstract method in the Animal class
	@Override
	public void makeNoise() {
		System.out.println("Lion noise: " + this.animalNoise);
	}
}
