package com.revature.oop;

public interface AnimalInterface {
	
	boolean IS_ANIMAL = true;
	
	void setNumLegs(int numLegs);
	
	int getNumLegs();
	
	void setFly(boolean fly);
	
	boolean getFly();
	
	void setSound(String sound);
	
	String getSound();
}
