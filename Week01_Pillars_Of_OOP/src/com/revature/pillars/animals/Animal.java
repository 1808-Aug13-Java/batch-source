package com.revature.pillars.animals;


/** This is a base class that defines some basic behavior for an animal. Other
 * classes can extend it to define specific animals. */
public abstract class Animal {
	
	// The following are examples of encapsulation as they use the protected 
	// modifier to allow access to subclasses of animals. 
	/** The speed at which the animal travels. This is measured in arbitrary 
	 * units. */
	protected double speed;
	
	/** The number of legs that the animal has. */
	protected int legs;
	
	/** The x position of the animal. */
	protected double x;
	
	/** The y position of the animal. */
	protected double y;
	
	/** The direction, in terms of degrees, that the animal is traveling. 
	 * This is implemented how a unit circle would be, where 0 is towards 
	 * the +x direction and 90 is towards the +y direction. */
	protected double direction;
	
	
	//Example of overloaded constructors in example of polymorphism
	/** Constructs a new animal with 2 legs by default that is not moving
	 * and starts at (0,0). */
	public Animal() {
		this(0d, 0, 0d, 0d, 0d);
	} // end of constructor Animal
	
	/** Constructs a new animal with the specified number of legs that is 
	 * not moving and starts at (0,0). 
	 * @param legs - The number of legs the animal has */
	public Animal(int legs) {
		this(0d, legs, 0d, 0d, 0d);
	} // end of constructor Animal
	
	/** Constructs a new animal object with the specified parameters. 
	 * @param speed - The speed at which the animal will travel in a given 
	 * direction
	 * @param legs - The number of legs the animal has
	 * @param x - The x position of the animal
	 * @param y - The y position of the animal
	 * @param direction - the direction, in degrees, in which the animal
	 * will travel */
	public Animal(double speed, int legs, double x, double y, double direction) {
		this.speed = speed;
		this.legs = legs;
		this.x = x;
		this.y = y;
		this.direction = direction;
	} // end of constructor Animal
	
	
	
	
} // end of class Animal






