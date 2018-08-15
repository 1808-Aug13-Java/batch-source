package com.revature.pillars.animals;

import com.revature.exceptions.NegativeNumberException;

// This class shows inheritance as it receives most of its functionality 
// from it's parent class, Animal. 

/** A class that defines a dog. It can move about and it can make dog 
 * noises. */
public class Dog extends Animal {
	
	private static final int DEFAULT_DOG_LEGS = 4;
	
	/** Constructs a new dog that is not moving and starts at (0,0). */
	public Dog() {
		super(DEFAULT_DOG_LEGS);
	} // end of constructor Dog
	
	/** Constructs a new dog object with the specified parameters. 
	 * @param speed - The speed at which the animal will travel in a given 
	 * direction. Cannot be negative
	 * @param x - The x position of the animal
	 * @param y - The y position of the animal
	 * @param direction - the direction, in degrees, in which the animal
	 * will travel 
	 * @throws NegativeNumberException If {@code speed} is negative. */
	public Dog(double speed, double x, double y, double direction) {
		super(speed, DEFAULT_DOG_LEGS, x, y, direction);
	} // end of constructor Dog
	
	/** Makes a dog specific noise by printing to the console. */
	@Override
	public void makeNoise() {
		System.out.println("BORK!");
	} // end of makeNoise
	
	// Demonstrates the use of an exception
	
	/** Make a large amount of noise by repeatedly printing dog noises to 
	 * the console. 
	 * @param amountOfNoise - Number of times to print noise to the console 
	 * @throws NegativeNumberException If a negative number is provided. */
	public void makeLotsOfNoise(int amountOfNoise) 
	{
		// If the provided number is negative, throw exception
		if (amountOfNoise < 0) {
			throw new NegativeNumberException("Amount of Noise cannot be "
					+ "negative. value provided: " + amountOfNoise);
		}
		
		for (int i=0; i<amountOfNoise; i++) {
			makeNoise();
		}
	} // end of makeLotsOfNoise
	
} // end of class
