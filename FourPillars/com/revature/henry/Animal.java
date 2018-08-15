package com.revature.henry;
/* 
 * This class uses abstraction as the program is intended to be used by the user. It is important as it is used
 * by the other animal classes. This class also is the superclass of the other animal classes, as the other classes
 * inherit traits from this class.
 */
public abstract class Animal {
	
	private String name; //name of the animal
	private String diet; //Herbivore, Omnivore, or Carnivore
	private int mass;	 //Average mass of animal

/*
 * These variables are private, as they are specific to the animal and should not be altered.
 * This is encapsulation as I am controlling what data is accessible
 */
	public Animal(String name, String diet, int mass) {
		super();

		this.name = name;
		this.diet = diet;
		this.mass = mass;
		
	}
/*
 * The following methods are an example of polymorphism. Each method is used by the animal subclasses that I have been setup,
 * but the results are different for each one.
 */
	public void animalname() {
		System.out.println("The animal we are looking at: "+name);
	}
	public void animaldiet() {
		System.out.println("This animal's diet: "+diet);
	}
	public void animalmass() {
		System.out.println("Animal's Mass in grams: "+mass);
	}
	
	
}
