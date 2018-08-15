package pillars.driver;

import pillars.animals.*;
import pillars.exceptions.*;

public class AnimalDriver {

	public static void main(String[] args)
	{
		Mammal cat = new Cat("Trooper");
		Mammal dog = new Collie("Oreo");
		
		Reptile snake = new Snake("Nagini");
		Reptile liz = new Lizard("Bongo");
		
		// As exceptions are demonstrated, Abstraction is also being demonstrated. The "growHair"
		// Method is part of the Mammal interface but cats and collies grow hair at different rates
		
		// safeToGrowHair also demonstrates Polymorphism as the Animal Class has an implementation of the 
		// 		safeToGrowHair method. On a Reptile, this version is called. On a cat or collie, their own
		// 		version runs
		
		// Demonstrates the Unchecked Exception as we can check the condition prior to risking an exception
		while(((Cat) cat).safeToGrowHair())
		{
			try {
				System.out.println("cat hair is now: " + cat.growHair());
			} catch (HairException e) {
				System.out.println("Cat Hair growth failed (this should not print)");
			}
		}
		
		// Demonstrates the Checked expression as we can't know it will fail ahead of time
		try {
			((Snake)snake).heatUp();
			System.out.println("Snake heated. Heat source was sufficient!");
		} catch (InsufficientHeatException e) {
			System.out.println("Snake failed to heat! " + e.getMessage());
		}
		
		try {
			((Lizard)liz).heatUp();
			System.out.println("Lizard heated. Heat source was sufficient!");
		} catch (InsufficientHeatException e) {
			System.out.println("Lizard failed to heat! " + e.getMessage());
		}
		
		// Demonstrating Inheritance as each method is a call to the base Animal method
		// Also Demonstrates Encapsulation as the public methods are the only way to access private 
		//		fields "name" and "species", which are set up during construction. They never change
		System.out.println(((Cat)cat).getName() + " is a " + ((Cat)cat).getSpecies());
		System.out.println(((Collie)dog).getName() + " is a " + ((Collie)dog).getSpecies());
		System.out.println(((Lizard)liz).getName() + " is a " + ((Lizard)liz).getSpecies());
		System.out.println(((Snake)snake).getName() + " is a " + ((Snake)snake).getSpecies());
		
		
	}

}
