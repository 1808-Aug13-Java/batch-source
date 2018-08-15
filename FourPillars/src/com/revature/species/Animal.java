package com.revature.species;

public class Animal implements AnimalInterface {
	protected int speed;
	protected boolean hasWings;
	protected boolean hasLegs;
	protected boolean livesInWater;
	
	public Animal() {// constructor
		super();
	}
	
	public Animal(int speed, boolean hasWings, boolean hasLegs, boolean livesInWater) { // constructor
		this.speed = speed;
		this.hasWings = hasWings;
		this.hasLegs = hasLegs;
		this.livesInWater = livesInWater;
	}
	
	/**
	 * The following implements the getters and setters as seen in the AnimalInterface
	 */	

	public int getSpeed() { // getter
		return speed;
	}

	public void setSpeed(int speed) { // setter
		this.speed = speed;
	}

	public boolean isHasWings() {// getter
		return hasWings;
	}

	public void setHasWings(boolean hasWings) { // setter
		this.hasWings = hasWings;
	}

	public boolean isHasLegs() { // getter
		return hasLegs;
	}

	public void setHasLegs(boolean hasLegs) { // setter
		this.hasLegs = hasLegs;
	}

	public boolean isLivesInWater() { // getter
		return livesInWater;
	}

	public void setLivesInWater(boolean livesInWater) { // setter
		this.livesInWater = livesInWater;
	}
	
	/**
	 * The following overrides the equals method for more meaningful comparison
	 * An example of polymorphism through overriding
	 */	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		Animal otherObj = (Animal) obj;
		if (speed != otherObj.speed) {
			return false;
		}
		if(hasWings != otherObj.hasWings) {
			return false;
		}
		if(hasLegs != otherObj.hasLegs) {
			return false;
		}
		if(livesInWater != otherObj.livesInWater) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * The following overrides the toString method for more meaningful output
	 * An example of polymorphism through overriding
	 */
	@Override
	public String toString() {
		return ("Animal speed: "+speed + 
				"\n Animal has wings?: "+hasWings + 
				"\n Animal has legs?: "+hasLegs + 
				"\n Animal habitat in water?: "+livesInWater);
	}
	
} // end class