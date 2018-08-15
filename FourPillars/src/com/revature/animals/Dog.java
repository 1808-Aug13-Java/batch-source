package com.revature.animals;

public class Dog extends Animal { // Inheritance from Animal class (Pillar of OOP 1)
	
	private boolean hasBone; // this private variable can only be used by instances of the Dog class, 
	// which demonstrates Encapsulation (Pillar of OOP 3)
	
	// no-argument constructor
	public Dog() {
		super();
		this.animalNoise = "Bark!";
		this.numberOfLegs = 4;
		this.weight = 25;
		this.isDomesticated = true;
		this.hasBone = false;
		this.gender = "female";
	}
	
	// this method is specific to the Dog class
	// if a subclass of Dog was created, this method can be inherited
	public void giveDogABone() {
		this.hasBone = true;
		System.out.println("You gave a bone to the dog.");
	}
	
	// this method overrides the abstract Animal method
	@Override
	public void makeNoise() {
		System.out.println("Dog noise: " + this.animalNoise);
	}
	
	// the following methods override default implementation
	@Override
	public void walk() {
		System.out.println("Dog is walking!");
	}
	
	@Override
	public void attack() {
		if (this.hasBone) {
			System.out.println("This dog has a bone and is too busy to attack.");
		} else {
			System.out.println("This dog is attacking! Someone should have thrown it a bone!");
		}
	}
}
