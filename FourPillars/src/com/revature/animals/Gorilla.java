package com.revature.animals;

public class Gorilla extends Animal { // Inheritance from Animal class (Pillar of OOP 1)
	
	private boolean hasBanana = false; // this variable is hidden and can only be accessed by an instance of the Gorilla class
	// this demonstrates Encapsulation (pillar of OOP 3)
	
	// constructor
	public Gorilla() {
		super();
		this.numberOfLegs = 2;
		this.weight = 800;
		this.animalNoise = "Roar!";
		this.isDomesticated = false;
		this.hasBanana = false;
		this.gender = "male";
	}
	
	// the following method specifies an implementation for the abstract Animal method makeNoise()
	@Override
	public void makeNoise() {
		System.out.println("Gorilla noise: " + this.animalNoise);
	}
	
	// this method is specific to the Gorilla class but can be inherited by subclasses
	public void giveBanana() {
		this.hasBanana = true;
		System.out.println("You threw a banana at the gorilla. Good thing you didn't make him angry.");
	}
	
	// the following two methods override the default Animal class implementation
	// and demonstrate Polymorphism (pillar of OOP 2)
	@Override
	public void walk() {
		System.out.println("Gorilla is lumbering along!");
	}

	@Override
	public void attack() {
		if (this.hasBanana) {
			System.out.println("This gorilla has a banana. He is too busy eating to attack (for now).");
		} else {
			System.out.println("Gorilla is attacking! Somebody should have thrown him a banana!");
		}
	}
	
	// this method is specific to the Gorilla class and any subclasses that extend it, if they exist
	public void die() {
		System.out.println("Gorilla died, giving birth to memes everywhere.");
	}
	
}
