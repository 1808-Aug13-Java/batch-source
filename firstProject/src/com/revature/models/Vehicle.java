package com.revature.models;

import com.revature.exceptions.NegativeSpeedException;

public class Vehicle {

	protected int speed;
	protected boolean hasWheels;
	protected int direction;
	
	//default constructor
	public Vehicle() {
		super();
	}
	
	public Vehicle(int speed, boolean hasWheels, int direction) {
		this.speed = speed;
		this.hasWheels = hasWheels;
		this.direction = direction;
	}
	public void setSpeed(int newSpeed) throws NegativeSpeedException {
		if (newSpeed < 0) {
			try {
				throw new NegativeSpeedException("Speed can not go below zero.");
			} catch (NegativeSpeedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("Speed set to " + newSpeed);
			this.speed = newSpeed;	
		}
	}
	
	public static void staticMethod() {
		System.out.println("Static method from vehicle class.");
	}
	
	public void setHasWheels(boolean wheels) {
		this.hasWheels = wheels;
	}


	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getSpeed() {
		return speed;
	}

	public boolean isHasWheels() {
		return hasWheels;
	}
	
	public int getDirection() {
		return direction;
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
}
