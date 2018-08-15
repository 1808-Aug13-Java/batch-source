package com.revature.species;

import com.revature.exceptions.ImpossibleSpeedException;

public class Bat extends Animal { // Bat class is derived from Animal class; displays inheritance
	
	/*
	 * The following displays encapsulation as there are both members only visible/accessible to 
	 * the current class to prevent corruption in addition to the methods that are public
	 */
	
	/**
	 * The following fields are specific to a Bat, not necessarily an Animal
	 */
	private int wingspan;
	private String furColor;
	private int sonarFreq;
	
	public Bat() {
		super();
	}
	
	public Bat(int speed, boolean hasWings, boolean hasLegs, boolean livesInWater) {
		super(speed, hasWings, hasLegs, livesInWater);
	}
	
	/**
	 * Gets the wingspan of bat
	 * @return wingspan
	 */
	public int getWingspan() {
		return wingspan;
	}

	public void setWingspan(int wingspan) {
		this.wingspan = wingspan;
	}

	/**
	 * Gets the fur color
	 * @return the fur color
	 */
	public String getFurColor() {
		return furColor;
	}
	
	/**
	 * Gets the fur color based on the species of the bat; example of overloading
	 * An example of polymorphism through overloading
	 * @return the fur color
	 */
	public String getFurColor(String species) {
		return furColor;
		
	}

	public void setFurColor(String furColor) {
		this.furColor = furColor;
	}

	/**
	 * Gets the pitch of the dolphin
	 * @return the pitch (db)
	 */
	public int getSonarFreq() { // note: bat sonar typically ranges from 10–120 kHz
		return sonarFreq;
	}

	public void setSonarFreq(int sonarFreq) {
		this.sonarFreq = sonarFreq;
	}
	
	/**
	 * This method sets the speed of the bat, which cannot be greater than 100 mph
	 * else it may throw a runtime/unchecked exception
	 */
	@Override
	public void setSpeed(int speed) {
		if (speed > MAX_SPEED) { // MAX_SPEED is 100 mph
			try {
				throw new ImpossibleSpeedException("Bat cannot travel faster than 100 mph. Free-tailed bat is"
						+ "fastest animal in the world traveling at 100 mph");
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