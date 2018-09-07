package com.revature.fourpillars;

/*
 * This Abstract class serves as the base case for all animals being represented in this simple project.
 * Every animal must be of some size, and must eat. They will either eat other animals, is a predator, or
 * gathers food, or does both. This outline of the base case for all creatures extending this class
 * provides abstraction and allows a programmer to use this class without being overly concerned 
 * with how it works.
 */
public abstract class Animal {

	
/* 
 * By making these variables private, they can only be accessed with the getters and setters in 
 * this class creates encapsulation by wrapping the data members and their methods of accessing
 * them into one unit
 */
	protected String size;
	protected boolean isPredator;
	protected boolean isGatherer;

	//no arg constructor
	public Animal() {
		super();
	}
	//constructor accepting values to set the variables to
	public Animal(String size, boolean isPredator, boolean isGatherer) {
		super();
		this.size = size;
		this.isPredator = isPredator;
		this.isGatherer = isGatherer;
	}
	
	//this abstract method ensures that any object extending my animal class must implement this method
	abstract public void setClassification();
	
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public boolean isPredator() {
		return isPredator;
	}

	public void setPredator(boolean isPredator) {
		this.isPredator = isPredator;
	}

	public boolean isGatherer() {
		return isGatherer;
	}

	public void setGatherer(boolean isGatherer) {
		this.isGatherer = isGatherer;
	}
	
	@Override
	public String toString() {
		return "Animal [size=" + size + ", isPredator=" + isPredator + ", isGatherer=" + isGatherer + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isGatherer ? 1231 : 1237);
		result = prime * result + (isPredator ? 1231 : 1237);
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		if (isGatherer != other.isGatherer)
			return false;
		if (isPredator != other.isPredator)
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		return true;
	}
	
	
}
