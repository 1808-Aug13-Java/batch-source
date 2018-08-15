package com.revature.oop;

public class Animal {

	protected String animalClass;
	protected boolean eats;
	protected int legs;
	
	public Animal() {
		super();
	}

	public Animal( int legs, boolean carnivore) {
		this.legs = legs;
		this.carnivore = carnivore;
	}

	public String getAnimalClass() {
		return animalClass;
	}

	public void setAnimalClass(String animalClass) {
		this.animalClass = animalClass;
	}

	public boolean isCarnivore() {
		return carnivore;
	}

	public void setCarnivore(boolean carnivore) {
		this.carnivore = carnivore;
	}

	public int getLegs() {
		return legs;
	}

	public void setLegs(int legs) {
		this.legs = legs;
	}
	
	
}
