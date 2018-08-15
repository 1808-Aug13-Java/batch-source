package com.revature.animals;

import com.revature.exceptions.UncheckedAnimalException;

public class Snake extends Animal { // Inheritance from Animal class (Pillar of OOP 1)
	
	// this is a no-argument constructor
	public Snake() {
		super();
		this.animalNoise = "Hiss!";
		this.isDomesticated = false;
		this.numberOfLegs = 0;
		this.weight = 5;
		this.gender = "male";
	}
	
	// method overriding, demonstrating Polymorphism (Pillar of OOP 2)
	@Override
	public void makeNoise() {
		System.out.println("Snake noise: " + this.animalNoise);
	}
	
	@Override
	public void attack() {
		System.out.println("The snake is lunging at you, fangs bared!");
	}
	
	// the following method is specific to objects of the Snake class
	// if a subclass of Snake was created, this method can be inherited
	public void slither() {
		System.out.println("The snake is slithering!");
	}

	// this method throws a runtime exception if it is called
	public void killProgram() throws UncheckedAnimalException {
		throw new UncheckedAnimalException();
	}
}
