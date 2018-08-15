package com.revature.pillars.animals;

import com.revature.exceptions.NegativeNumberException;
import com.revature.exceptions.NoLegsException;

//This class shows inheritance as it receives most of its functionality 
//from its parent class, Animal. 

/** A class that defines a fish. It can move about and it can make fish 
* noises. */
public class Fish extends Animal {
	
	/** The default number of legs to give a fish. */
	private static final int DEFAULT_FISH_LEGS = 0;
	
	/** Constructs a new cat that is not moving and starts at (0,0). */
	public Fish() {
		super(DEFAULT_FISH_LEGS);
	} // end of constructor Fish
	
	/** Constructs a new fish object with the specified parameters. 
	 * @param speed - The speed at which the fish will travel in a given 
	 * direction. Cannot be negative
	 * @param x - The x position of the fish
	 * @param y - The y position of the fish
	 * @param direction - the direction, in degrees, in which the fish
	 * will travel 
	 * @throws NegativeNumberException If {@code speed} is negative. */
	public Fish(double speed, double x, double y, double direction) {
		super(speed, DEFAULT_FISH_LEGS, x, y, direction);
	} // end of constructor Fish
	
	
	// An example of improper inheritance. Also, an excuse to make a  
	// checked exception. 
	/** This function is not implemented for fish. 
	 * @throws NoLegsException Fish do not have legs to walk on. */
	public void walk() throws NoLegsException {
		throw new NoLegsException("Fish cannot walk. ");
	} // end of walk
	
	
	/** Makes a fish specific noise by printing to the console. */
	@Override
	public void makeNoise() {
		System.out.println("Glub!");
	} // end of makeNoise
	
} // end of class Fish
