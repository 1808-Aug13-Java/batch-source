package com.revature.species;

import com.revature.exceptions.ImpossibleColorException;
import com.revature.exceptions.ImpossibleSpeedException;

public class Dolphin extends Animal { // Dolphin class is derived from Animal class; displays inheritance
	
	/*
	 * The following displays encapsulation as there are both members only visible/accessible to 
	 * the current class to prevent corruption in addition to the methods that are public
	 */
	
	/**
	 * The following fields are specific to a Dolphin, not necessarily an Animal
	 */
	private int pitch;
	private int sonarFreq1;
	private int dorsalFinCurve; // in degrees
	private String shadeOfGray; // dolphins are not white or black
	
	public Dolphin() { // constructor w/out parameters
		super();
	}
	
	
	public Dolphin(int speed, boolean hasWings, boolean hasLegs, boolean livesInWater) { // constructor w parameters
		super(speed, hasWings, hasLegs, livesInWater);
	}
	
	/* 
	 * The following are accessors and mutators for the Dolphin class
	 *
	 */
	
	/**
	 * Gets the pitch of the dolphin
	 * @return the pitch (db)
	 */
	public int getPitch() {
		return pitch;
	}

	public void setPitch(int pitch) {
		this.pitch = pitch;
	}

	/**
	 * Gets the echolocation frequency
	 * @return the frequency
	 */
	public int getsonarFreq1() {
		return sonarFreq1;
	}

	public void setsonarFreq1(int sonarFreq1) {
		this.sonarFreq1 = sonarFreq1;
	}

	/**
	 * Gets the dorsal fin curve
	 * @return the dorsal fin curve as integer because measured in degrees
	 */
	public int getDorsalFinCurve() {
		return dorsalFinCurve;
	}

	public void setDorsalFinCurve(int dorsalFinCurve) {
		this.dorsalFinCurve = dorsalFinCurve;
	}

	/**
	 * Gets the shade of gray
	 * @return the dolphin's shade of gray
	 */
	public String getShadeOfGray() {
		return shadeOfGray;
	}

	/**
	 * Sets the shade of gray represented by #s
	 * if the input is not gray then this is not the proper color of dolphin
	 * this can throw checked exception
	 */
	public void setShadeOfGray(String shadeOfGray) {
		if (shadeOfGray != "gray") {
			try {
				throw new ImpossibleColorException("Dolphin can only be shades of gray.");
			} catch (ImpossibleColorException e) {
				System.out.println("exception handled");
			}
		}
		else {
		
		this.shadeOfGray = shadeOfGray;
		}
	}
	
	/**
	 * This method sets the speed of the dolphin, which cannot be greater than 100
	 * else it may throw a runtime/unchecked exception
	 */
	@Override
	public void setSpeed(int speed) {
		if (speed > MAX_SPEED) { // MAX_SPEED is 100 mph
			try {
				throw new ImpossibleSpeedException("Dolphin cannot travel faster than 100 mph.");
			} catch (ImpossibleSpeedException e) {
				System.out.println("exception handled");
			}
		}
		else {
			this.speed = speed;
		}
	}

	/**
	 * The following method overrides the equals method for more meaningful comparison
	 * @return a boolean determining if the two objects are equal
	 */	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
}
