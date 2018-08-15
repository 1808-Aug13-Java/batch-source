package com.revature.animals;

import com.revature.exceptions.NegativeSpeedException;
import com.revature.exceptions.NegativeNumApendagesException;

public class AnimalBase {
	
	// Encapsulation
	// Access modifiers to prevent public access but allow subclasses to access
	protected int numApendages;
	protected int landSpeed;
	protected boolean hasTail;
	
	// default constructor
	public AnimalBase() {
		super();
	}
	
	// Polymorphism
	// Overloading of default constructor
	public AnimalBase(int numApendages, int landSpeed, boolean hasTail) {
		this.numApendages = numApendages;
		this.landSpeed = landSpeed;
		this.hasTail = hasTail;
	}

	// Getters and Setters
	public int getNumApendages() {
		return numApendages;
	}

	public void setNumApendages(int numApendages) throws NegativeNumApendagesException {
		if (numApendages < 0) {
			throw new NegativeNumApendagesException("Number of Apendages cannot be negative");
		}
		else {
		this.numApendages = numApendages;
		}
	}

	public int getLandSpeed() {
		return landSpeed;
	}

	// Method to demonstrate Checked Exception handling
	public void setLandSpeed(int landSpeed) throws NegativeSpeedException {
		if (landSpeed < 0) {
			throw new NegativeSpeedException("Speed cannot be negative");
		}
		else {
			this.landSpeed = landSpeed;
		}
	}

	public boolean isHasTail() {
		return hasTail;
	}

	public void setHasTail(boolean hasTail) {
		this.hasTail = hasTail;
	}

	// Method Override of toString and equals
	@Override
	public String toString() {
		return "AnimalBase [numApendages=" + numApendages + ", landSpeed=" + landSpeed + ", hasTail=" + hasTail + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnimalBase other = (AnimalBase) obj;
		if (hasTail != other.hasTail)
			return false;
		if (landSpeed != other.landSpeed)
			return false;
		if (numApendages != other.numApendages)
			return false;
		return true;
	}
	
	
}
