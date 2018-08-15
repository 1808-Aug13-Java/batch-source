package com.revature.fourpillars;

public class Mammals {
	protected int numLegs;
	private boolean isInjured;
	private boolean isHungry;
	private boolean isSleeping;
	private String movement;
	
	public Mammals() {
		//insert exception here for animal
		super();
	}
	public Mammals(int numLegs, boolean isInjured, boolean isHungry) {
		this.numLegs=numLegs;
		this.isInjured=isInjured;
		this.isHungry= isHungry;
		this.movement= (numLegs <=2) ? "hops" : "trots";
	}
	
	public int getNumLegs() {
		return numLegs;
	}
	protected void setNumLegs(int legs) {
		this.numLegs= legs;
	}
	public boolean isInjured() {
		return isInjured;
	}
	public void setInjured(boolean isInjured) {
		this.isInjured = isInjured;
	}
	public boolean isHungry() {
		return isHungry;
	}
	public void setHungry(boolean isHungry) {
		this.isHungry = isHungry;
	}
	public boolean isSleeping() {
		return isSleeping;
	}
	protected void setSleeping(boolean isSleeping) {
		this.isSleeping = isSleeping;
	}
	public String getMovement() {
		System.out.println("This animal " + this.movement);
		return movement;
	}
	public void setMovement(String movement) {
		this.movement = movement;
	}
}


