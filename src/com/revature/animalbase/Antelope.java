package com.revature.animalbase;

public class Antelope extends Animal{
	/* Antelope embodies both Abstraction, and inheritance
	 * since it is a subclass of Animal, which itself is an abstract class.
	 * Antelope also provides its own walk method.
	 */
	public Antelope() {
		super();
	}
	public Antelope(int legs, boolean fur, int speed, String name) {
		super(legs, fur, speed, name);
	}
	public void walk() {
		System.out.println("The " + super.getName() +" gallops forth!");
	}
}
