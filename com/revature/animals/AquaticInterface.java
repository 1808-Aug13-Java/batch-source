package com.revature.animals;

// Abstraction of aquatic animals using interface 
public interface AquaticInterface {
	
	void swim();

	default void dive() {
		System.out.println("Aquatic animal dives deeper");
	}
}
