package com.revature.models;

import java.io.Serializable;

import com.revature.exceptions.NegativeSpeedException;

public class HybridCar extends Vehicle implements Serializable, Cloneable{
	
	private int chargeTime;
	private String model;
	private String make;
	
	public HybridCar() {
		super();
	}
	
	public HybridCar(int chargeTime, String model, String make, int speed, boolean hasWheels, int direction) {
		super(speed, hasWheels, direction);
		this.chargeTime = chargeTime;
		this.model = model;
		this.make = make;
	}
	public int getChargeTime() {
		return chargeTime;
	}
	public void setChargeTime(int chargeTime) {
		this.chargeTime = chargeTime;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + chargeTime;
		result = prime * result + ((make == null) ? 0 : make.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		return result;
	}
	
	@Override
	public void setSpeed(int newSpeed) throws NegativeSpeedException {
		if (newSpeed < 0) {
			throw new NegativeSpeedException("Speed can not go below zero.");
		}else {
			System.out.println("Hybrid car speed set to " + newSpeed);
			this.speed = newSpeed;	
		}
	}

	public static void staticMethod() {
		System.out.println("Static method from hybrid car class.");
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
}
