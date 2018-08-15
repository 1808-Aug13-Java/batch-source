package com.revature.oop;

public class Octopus extends Animal {
	int ink = 500;
	public Octopus() {
		super(8, false, "Octopus Sound!");
	}
	
	public Octopus(int numLegs, boolean fly, String sound) {
		super(numLegs, fly, sound);
	}
	
	
	public void inkSac() {
		System.out.println("The octopus sprays its ink! Its ink levels are now at " + --ink);
	}
}
