package com.revature.animals;

import com.revature.exceptions.NegativeSpeedException;
import com.revature.exceptions.NegativeNumApendagesException;


public class Driver {

	public static void main(String[] args) {

		// Polymorphism - Covariance
		// puppy1 can only use methods defined by AnimalBase despite being
		// created through a Dog constructor
		AnimalBase puppy1 = new Dog();
		try {	// Checked exception handling
			puppy1.setLandSpeed(2);
		} catch (NegativeSpeedException e) {
			e.printStackTrace();
		}
		System.out.println("Puppy1 has landSpeed: " + puppy1.getLandSpeed());
		System.out.println();
		
		// puppy2 can call methods from Dog
		// it also inherits methods from AnimalBase parent allowing it to call setLandSpeed
		Dog puppy2 = new Dog();
		try {	// Checked exception handling
			puppy2.setLandSpeed(3);
		} catch (NegativeSpeedException e) {
			e.printStackTrace();
		}
		puppy2.setGoodBoy(true);
		System.out.println("Puppy2 has landSpeed: " + puppy2.getLandSpeed());
		System.out.println("Puppy2 is a good boy: " + puppy2.isGoodBoy());
		System.out.println();
		
		// goldfish implements AquaticInterface
		// This allows goldfish to call dive() and swim()
		Fish goldfish = new Fish();
		goldfish.swim();
		goldfish.setCurrDepth(1);
		System.out.println("goldfish currDepth pre-dive: " + goldfish.getCurrDepth());
		goldfish.dive();
		System.out.println("goldfish currDepth post-dive: " + goldfish.getCurrDepth());
		System.out.println();
		
		Rabbit bugs = new Rabbit(4, 20, true);
		System.out.println(bugs.toString());
		System.out.println();
		
		Turtle oldTurtle = new Turtle(6, 9);
		System.out.println(oldTurtle.toString());
		System.out.println();
		
		// Unchecked exception in setNumApendages
		// Program will exit after exception is thrown
		goldfish.setNumApendages(-1);
		
	}

}
