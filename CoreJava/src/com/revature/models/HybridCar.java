package com.revature.models;

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
	public void setSpeed(int speed) {
		if(speed < 0) {
			System.out.println("cannot go a negative speed");
		} else {
			System.out.println("hybrid car speed set to "+speed);
			this.speed = speed;
		}
	}
	
	public static void staticMethod() {
		System.out.println("static method from HybridCar class");
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
		return "HybridCar [chargeTime=" + chargeTime + ", model=" + model + ", make=" + make + "]";
	}
	
	

}
