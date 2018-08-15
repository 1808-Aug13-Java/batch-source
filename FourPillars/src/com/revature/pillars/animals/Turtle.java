package com.revature.pillars.animals;

import com.revature.exceptions.NegativeNumberException;

//This class shows inheritance as it receives most of its functionality 
//from its parent class, Animal. 

/** A class that defines a turtle. It can move about and it can make turtle 
* noises. */
public class Turtle extends Animal {
	
	/** The default number of legs to give a turtle. */
	private static final int DEFAULT_TURTLE_LEGS = 0;
	
	/** Constructs a new cat that is not moving and starts at (0,0). */
	public Turtle() {
		super(DEFAULT_TURTLE_LEGS);
	} // end of constructor Turtle
	
	/** Constructs a new turtle object with the specified parameters. 
	 * @param speed - The speed at which the turtle will travel in a given 
	 * direction. Cannot be negative
	 * @param x - The x position of the turtle
	 * @param y - The y position of the turtle
	 * @param direction - the direction, in degrees, in which the turtle
	 * will travel 
	 * @throws NegativeNumberException If {@code speed} is negative. */
	public Turtle(double speed, double x, double y, double direction) {
		super(speed, DEFAULT_TURTLE_LEGS, x, y, direction);
	} // end of constructor Turtle
	
	
	/** Makes a turtle specific noise by printing to the console. */
	@Override
	public void makeNoise() {
		System.out.println("Ahhh");
	} // end of makeNoise
	
} // end of class Turtle

