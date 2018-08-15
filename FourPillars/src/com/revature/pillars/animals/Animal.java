package com.revature.pillars.animals;

import java.awt.Point;

import com.revature.resources.MathHelper;

/** This is a base class that defines some basic behavior for an animal. Other
 * classes can extend it to define specific animals. */
public abstract class Animal {
	
	// The following are examples of encapsulation as they use the private 
	// modifier to prevent free access to the variables. Instead, getters and 
	// setters are provided to give restricted access. 
	/** The speed at which the animal travels. This is measured in arbitrary 
	 * units. */
	private double speed;
	
	/** The number of legs that the animal has. */
	private int legs;
	
	/** The x position of the animal. */
	private double x;
	
	/** The y position of the animal. */
	private double y;
	
	/** The direction, in terms of degrees, that the animal is traveling. 
	 * This is implemented how a unit circle would be, where 0 is towards 
	 * the +x direction and 90 is towards the +y direction. */
	private double direction;
	
	
	//Example of overloaded constructors in example of polymorphism
	/** Constructs a new animal with 0 legs by default that is not moving
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

	
	
	
	
	
	
	/** Returns the current speed of the animal, represented by arbitrary 
	 * units.
	 * @return The speed of the animal */
	public double getSpeed() {
		return speed;
	} // end of getSpeed
	
	/** Returns the number of legs the animal has. 
	 * @return The number of legs the animal has*/
	public int getLegs() {
		return legs;
	} // end of getLegs
	
	/** Returns the current position x position of the animal on the x,y 
	 * plane. 
	 * @return The x position of the animal */
	public double getX() {
		return x;
	} // end of getX
	
	/** Returns the current position x position of the animal on the x,y 
	 * plane. 
	 * @return The x position of the animal */
	public double getY() {
		return y;
	} // end of getY
	
	/** Returns the current direction of the animal represented in degrees, 
	 * where 0 degrees is +x direction and 90 is +y direction.  */
	public double getDirection() {
		return direction;
	} // end of getDirection
	
	
	
	
	
	/** Sets the speed at which the animal moves. More formally, the animal 
	 * should move the provided arbitrary distance every time {@code Animal.move}
	 * is called. 
	 * @param speed - The new speed of the animal */
	public void setSpeed(double speed) {
		this.speed = speed;
	} // end of setSpeed
	
	/** Sets a new X coordinate for the animal. 
	 * @param x - The new X coordinate for the animal */
	public void setX(double x) {
		this.x = x;
	} // end of setX
	
	/** Sets a new Y coordinate for the animal. 
	 * @param y - The new Y coordinate for the animal */
	public void setY(double y) {
		this.y = y;
	} // end of setY
	
	
	// The following shows polymorphism as there are two methods that are 
	// overloaded. 
	/** Sets the X and Y coordinates for the animal. 
	 * @param x - The new X coordinate for the animal
	 * @param y - The new Y coordinate for the animal */
	public void setCoordinates(double x, double y) {
		this.x = x;
		this.y = y;
	} // end of setCoordinates
	
	/** Sets the X and Y coordinates for the animal. 
	 * @param coordinates - A point object containing new X,Y coordinates */
	public void setCoordinates(Point coordinates) {
		this.x = coordinates.getX();
		this.y = coordinates.getY();
	} // end of setCoordinates
	
	
	/** Sets the direction that the animal is traveling, represented in 
	 * degrees around a unit circle. Eg: 0 degrees is a +X direction 
	 * while 90 degrees is a +Y direction. */
	public void setDirection(double direction) {
		this.direction = direction;
	} // end of setDirection
	
	
	// The following shows abstracion, specifically how some functions can 
	// bemade abstract in general wit specific implementations. 
	
	
	// A function for the movement of animals, as (most) all animals 
	// can move. The few that don't can override such functionality. 
	
	/** Moves the animal in the direction that the animal is facing, updating 
	 * the X and Y coordinates accordingly. */
	public void move() {
		// Compute the change in position
		double difX = speed * Math.cos(MathHelper.degreesToRadians(direction));
		double difY = speed * Math.sin(MathHelper.degreesToRadians(direction));
		
		// Update the position relative to the old position
		setX(getX() + difX);
		setY(getY() + difY);
	} // end of move
	
	
	// A bad example of how to use abstraction to specify movement. This is 
	// because there are quite a few animals that don't have legs, and therefore
	// cannot walk.
	
	/** Moves the animal in the direction that the animal is facing, updating 
	 * the X and Y coordinates accordingly. Does nothing if the animal has 
	 * no legs. */
	public void walk() {
		// Just reuse the move function
		move();
	} // end of walk
	
	
	// An example of abstraction. 
	
	/** Makes an animal specific noise. */
	public abstract void makeNoise();
	
} // end of class Animal






