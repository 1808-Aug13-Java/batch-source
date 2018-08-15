package com.revature.animals;

public class Bird extends Animal {
	
	private double wingSpan;
	private boolean canFly;
	
	//Methods
	
	public Bird() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Bird(int age, int numLegs, String species, String diet, String huntMethod, double hunger) {
		super(age, numLegs, species, diet, huntMethod, hunger);
		// TODO Auto-generated constructor stub
	}
	
	//Setters and getters 
	public double getWingSpan() {
		return wingSpan;
	}
	
	public void setWingSpan(double wingSpan) {
		this.wingSpan = wingSpan;
	}
	
	public boolean isCanFly() {
		return canFly;
	}
	
	public void setCanFly(boolean canFly) {
		this.canFly = canFly;
	}
	
	//Hash Override
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (canFly ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(wingSpan);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
	//Equals Override 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bird other = (Bird) obj;
		if (canFly != other.canFly)
			return false;
		if (Double.doubleToLongBits(wingSpan) != Double.doubleToLongBits(other.wingSpan))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Bird [wingSpan=" + wingSpan + ", canFly=" + canFly + ", age=" + age + ", numLegs=" + numLegs
				+ ", species=" + species + ", diet=" + diet + ", huntMethod=" + huntMethod + ", hunger=" + hunger + "]";
	}
	
	
	
	
	
	

}
