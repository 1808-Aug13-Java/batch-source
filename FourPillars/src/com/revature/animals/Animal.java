package com.revature.animals;

public abstract class Animal {

	// protected variables are package-accessible and can be inherited
	protected int numberOfLegs;
	protected int weight;
	protected String animalNoise;
	protected boolean isDomesticated;
	protected String gender;
	
	// static constant belongs to Animal class
	public static final String STATIC_ANIMAL_DECLARATION = "I am an animal!"; 
	
	// same as default constructor if none were provided
	public Animal() {
		super();
	}
	
	public boolean isDomesticated() {
		return isDomesticated;
	}

	// this setter exists in case we want to domesticate an object of the Animal class
	public void setDomesticated(boolean isDomesticated) {
		this.isDomesticated = isDomesticated;
	}

	// the Object class's toString() method is overridden here, demonstrating Polymorphism (Pillar of OOP 2)
	@Override
	public String toString() {
		return "Animal [numberOfLegs=" + numberOfLegs + ", weight=" + weight + ", animalNoise=" + animalNoise
				+ ", isDomesticated=" + isDomesticated + ", gender=" + gender + "]";
	}

	// constructor overloading demonstrates Polymorphism (pillar of OOP 2)
	public Animal(int numberOfLegs, int weight, String animalNoise, boolean isDomesticated) {
		this.numberOfLegs = numberOfLegs;
		this.weight = weight;
		this.animalNoise = animalNoise;
		this.isDomesticated = isDomesticated;
	}
	
	// this method is abstract, needing to be implemented by all subclasses
	// that extend the Animal class
	public abstract void makeNoise();
	
	// the next two methods are concrete and provide a default implementation to subclasses
	public void walk() {
		if (numberOfLegs > 0) {
			System.out.println("Animal is walking!");
		} else {
			System.out.println("This animal cannot walk!");
		}
	}
	
	public void attack() {
		System.out.println("Animal is attacking!");
	}
}
