package com.revature.species;

/*
 * This is an interface that exemplifies abstraction
 */

public interface AnimalInterface {
	
	// variables in an interface are public, static and final; constants
	
	
	public static final int MAX_SPEED = 100; // maximum speed

	// members of an interface are implicitly public
	
	/**
	 * Gets the speed
	 * @return the speed
	 */
	public int getSpeed();

	/**
	 * Sets the speed
	 */
	public void setSpeed(int speed);

	/**
	 * Checks if there are wings
	 * @return true or false if animal has wings
	 */
	public boolean isHasWings();

	/**
	 * Sets wings
	 */
	public void setHasWings(boolean hasWings);

	/**
	 * Checks if there are legs
	 * @return true or false if animal has legs
	 */
	public boolean isHasLegs();

	/**
	 * Sets legs
	 */
	public void setHasLegs(boolean hasLegs);

	/**
	 * Checks if lives in water
	 * @return true or false if animal's habitat is in water
	 */
	public boolean isLivesInWater();

	/**
	 * Sets habitat
	 */
	public void setLivesInWater(boolean livesInWater);
	
	/**
	 * The following method overrides the equals method for more meaningful comparison
	 * @return a boolean determining if the two objects are equal
	 */	
	@Override
	public boolean equals(Object obj);
	
	/**
	 * The following method overrides the toString method for more meaningful output
	 * @return a string that contains info such as: speed of animal, whether it
	 * has legs, whether it has wings, and if it lives in water
	 */
	@Override
	public String toString();
	
} // end AnimalInterface
