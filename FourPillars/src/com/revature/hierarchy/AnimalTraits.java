package com.revature.hierarchy;

public abstract class AnimalTraits { //Aside from implementing an abstract class this class aims to keep things simple while most
									//of the intrusive details are separated among, human.java, lamb.java, robin.java and AnimalResults.java
	
	private boolean Mammal;			//
	private boolean Bird;
	private boolean Omnivore;
	private int numLegs;
	private boolean flies;
	
	public AnimalTraits() {
		
	}
	
	public AnimalTraits(boolean mam, boolean bir, boolean omni, int legs, boolean fly) {
		this.Mammal = mam;
		this.Bird = bir;
		this.Omnivore = omni;
		this.numLegs = legs;
		this.flies = fly;
	}
	
	public boolean isMammal() {
		return Mammal;
	}
	
	public boolean isBird() {
		return Bird;
	}
	
	public boolean isOmnivore() {
		return Omnivore;
	}
	
	public int getnumLegs() {
		return numLegs;

	}
	
	public boolean getflies() {
		return flies;
	}
	
}
