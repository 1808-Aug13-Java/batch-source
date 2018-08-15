package com.revature.animalbase;
//This is inheritance of a class that extended an abstract class.
public class AnglerFish extends Fish{
	private int lureIntensity = 0;
	public AnglerFish() {
		super();
	}
	public AnglerFish(int numberLegs, boolean fur, int speed, String name) {
		super(numberLegs, fur, speed, name);
	}
	public int getLureIntensity() {
		return lureIntensity;
	}
	public void setLureIntensity(int lureIntensity) {
		this.lureIntensity = lureIntensity;
	}
}
