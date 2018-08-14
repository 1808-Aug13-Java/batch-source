package com.revature.models;

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
	
	public void setSpeed(int speed) {
		if(speed < 0) {
			System.out.println("cannot go a negative speed");
		} else {
			System.out.println("vehicle speed set to "+speed);
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

	public static void staticMethod() {
		System.out.println("static method from Vehicle class");
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
	
	
	
}
