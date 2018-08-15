package com.revature.species;

import com.revature.exceptions.ImpossibleSpeedException;

public class Elephant extends Animal { // Elephant class is derived from Animal class; displays inheritance
	
	/*
	 * The following displays encapsulation as there are both members only visible/accessible to 
	 * the current class to prevent corruption in addition to the methods that are public
	 */

	/**
	 * The following fields are specific to an Elephant, not necessarily an Animal
	 */
	private int lengthOfTrunk;
	private int thicknessOfHoof;
	
	public Elephant() { // constructor w/out parameters
		super();
	}
	
	public Elephant(int speed, boolean hasWings, boolean hasLegs, boolean livesInWater) { // constructor w parameters
		super(speed, hasWings, hasLegs, livesInWater);
	}

	/* 
	 * The following are accessors and mutators for the Dolphin class
	 *
	 */
	
	/**
	 * Gets the length of the trunk
	 * @return the length of trunk
	 */
	public int getLengthOfTrunk() {
		return lengthOfTrunk;
	}

	public void setLengthOfTrunk(int lengthOfTrunk) {
		this.lengthOfTrunk = lengthOfTrunk;
	}

	/**
	 * Gets the thickness of the hoof
	 * @return the length of hoof
	 */
	public int getThicknessOfHoof() {
		return thicknessOfHoof;
	}

	public void setThicknessOfHoof(int thicknessOfHoof) {
		
		this.thicknessOfHoof = thicknessOfHoof;
	}
	
	/**
	 * This method sets the speed of the elephant, which cannot be greater than 100 mph
	 * else it may throw a runtime/unchecked exception
	 */
	@Override
	public void setSpeed(int speed) {
		if (speed > MAX_SPEED) { // MAX_SPEED is 100 mph
			try {
				throw new ImpossibleSpeedException("Elephant definitely cannot travel over 100 mph.");
			} catch (ImpossibleSpeedException e) {
				System.out.println("exception handled");
			}
		}
		else {
			this.speed = speed;
		}
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
}