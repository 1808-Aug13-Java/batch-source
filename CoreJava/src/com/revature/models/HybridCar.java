package com.revature.models;

import com.revature.exceptions.NegativeSpeedException;

public class HybridCar extends Vehicle {
	
	private int chargeTime;
	private String model;
	private String make;
	
	public HybridCar() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public HybridCar(int speed, boolean hasWheels, int direction) {
		super(speed, hasWheels, direction);
		// TODO Auto-generated constructor stub
	}

	public int getChargeTime() {
		return chargeTime;
	}

	public void setChargeTime(int chargeTime) {
		this.chargeTime = chargeTime;
	}
	
	@Override
	public void setSpeed(int speed) throws NegativeSpeedException {
		if (speed < 0) {
			throw new NegativeSpeedException("Hybrid can't go in reverse");
		} else {
			System.out.println("Hybrid car speed set to " + speed);
			this.speed = speed;	
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + chargeTime;
		result = prime * result + ((make == null) ? 0 : make.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		HybridCar other = (HybridCar) obj;
		if (chargeTime != other.chargeTime)
			return false;
		if (make == null) {
			if (other.make != null)
				return false;
		} else if (!make.equals(other.make))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HybridCar [chargeTime=" + chargeTime + ", model=" + model + ", make=" + make + ", speed=" + speed
				+ ", hasWheels=" + hasWheels + ", direction=" + direction + "]";
	}
	
	public static void staticMethod() {
		System.out.println("Static method from HybridCar class");
	}
	
}
