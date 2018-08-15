package com.revature.pillars.animals;

import com.revature.exceptions.NegativeNumberException;

//This class shows inheritance as it receives most of its functionality 
//from its parent class, Animal. 

/** A class that defines a cat. It can move about and it can make cat 
* noises. */
public class Cat extends Animal {
	
	/** The default number of legs to give a cat. */
	private static final int DEFAULT_CAT_LEGS = 4;
	
	/** Constructs a new cat that is not moving and starts at (0,0). */
	public Cat() {
		super(DEFAULT_CAT_LEGS);
	} // end of constructor Cat
	
	/** Constructs a new cat object with the specified parameters. 
	 * @param speed - The speed at which the cat will travel in a given 
	 * direction. Cannot be negative
	 * @param x - The x position of the cat
	 * @param y - The y position of the cat
	 * @param direction - the direction, in degrees, in which the cat
	 * will travel 
	 * @throws NegativeNumberException If {@code speed} is negative. */
	public Cat(double speed, double x, double y, double direction) {
		super(speed, DEFAULT_CAT_LEGS, x, y, direction);
	} // end of constructor Cat
	
	
	/** Makes a cat specific noise by printing to the console. */
	@Override
	public void makeNoise() {
		System.out.println("MEW!");
	} // end of makeNoise
	
	
} // end of class Cat
