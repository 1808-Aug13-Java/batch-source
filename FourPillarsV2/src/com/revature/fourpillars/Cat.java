package com.revature.fourpillars;

import com.revature.customexceptions.CustomUncheckedException;

//Comments on how the four pillars of OOP are implemented are found in the Animals abstract class and the
//Seal class. This is because due to inheritance, polymorphism, abstraction, and encapsulation, I was able to
//easily and safely reuse code for each animal I did, with only slight modifications needed to make each 
//class meaningful for the specific animal.

public class Cat extends Animals {
	public Cat() {
		super();
	}
	
	public int getNumLegs() {
		return numLegs;
	}
	 
	public void setNumLegs(int numLegs) throws CustomUncheckedException {
		if(numLegs < 0) {
			try {
				throw new CustomUncheckedException("Error: numLegs cannot be negative"); 
			} catch (CustomUncheckedException e) {
				System.out.println("Error: numLegs cannot be negative");
			}
		} else {
		this.numLegs = numLegs;
		}
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public void speak() {
		System.out.println("meow");
	}
	
	public void move() {
		System.out.println("stalks");
	}


}
