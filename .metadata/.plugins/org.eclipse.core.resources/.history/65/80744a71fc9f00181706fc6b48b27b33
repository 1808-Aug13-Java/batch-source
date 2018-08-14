package com.revature.models;

public class Hybrid extends Vehicle {
	
	private int chargeTime;
	private String model;
	private String make;
	
	
	
	public Hybrid() {
		super();
	}
	
	public Hybrid(int speed, boolean hasWheels, int direction) {
		super(speed, hasWheels, direction);
	}
	
	
	@Override
	public void setSpeed(int speed) {
		if(speed < 0) {
			System.out.println("cannot go a negative speed");
		} else {
			System.out.println("Setting hybrid car speed to: " + speed);
			this.speed = speed;
		}
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
	
	public static void staticMethod() {
		System.out.println("static method from Hybrid class");
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
		Hybrid other = (Hybrid) obj;
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
		return "Hybrid [chargeTime=" + chargeTime + ", model=" + model + ", make=" + make + ", speed=" + speed
				+ ", hasWheels=" + hasWheels + ", direction=" + direction + "]";
	}
	
	
	
	
	
	
}
