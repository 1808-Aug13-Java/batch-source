package com.revature.models;

import com.revature.exceptions.NegativeSpeedException;

public class Vehicle {
	
	protected int speed;
	protected boolean hasWheels;
	protected int direction;
	
	public Vehicle() {
		super();
	}
	
	public Vehicle(int speed, boolean hasWheels, int direction) {
		this.speed = speed;
		this.hasWheels = hasWheels;
		this.direction = direction;
	}
	
	public void setSpeed(int speed) throws NegativeSpeedException {
		if (speed < 0) {
			throw new NegativeSpeedException("Cannot go negative speed");
		} else {
			System.out.println("Vehicle speed set to " + speed);
			this.speed = speed;	
		}
	}
	
	public int getSpeed() {
		return this.speed;
	}

	public boolean isHasWheels() {
		return hasWheels;
	}

	public void setHasWheels(boolean hasWheels) {
		this.hasWheels = hasWheels;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + direction;
		result = prime * result + (hasWheels ? 1231 : 1237);
		result = prime * result + speed;
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
		Vehicle other = (Vehicle) obj;
		if (direction != other.direction)
			return false;
		if (hasWheels != other.hasWheels)
			return false;
		if (speed != other.speed)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vehicle [speed=" + speed + ", hasWheels=" + hasWheels + ", direction=" + direction + "]";
	}
	
	public static void staticMethod() {
		System.out.println("Static method from Vehicle class");
	}
	
}
