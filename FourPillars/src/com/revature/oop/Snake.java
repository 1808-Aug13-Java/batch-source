package com.revature.oop;

import com.revature.exceptions.InvalidLegsException;
public class Snake extends Animal{ //I have this on each 'animal' class.  This shows that they
	int venom = 500;			  //inherit behaviors and variables from the Animal class
	public Snake() {			 //This is an example of the OOP Pillar: Inheritance
		super(0, false, "Hiss!");
	}
	
	public Snake(int numLegs, boolean fly, String sound) {
		super(numLegs, fly, sound);
	}
	
	@Override
	public void setNumLegs(int numLegs) {//Though setNumLegs is a method in Animal, this one
		this.numLegs = numLegs;			//is necessary because a snake having 0 legs is natural
		if(numLegs > 0) {			   //This is one of my examples of the OOP Pillar: Polymorphism
			try {
				throw new InvalidLegsException("This animal should have no legs!");
			} catch (InvalidLegsException e) {
				e.printStackTrace();
			}
		} else if(numLegs < 0) {
			try {
				throw new InvalidLegsException("As far as we know, having negative legs is impossible");
			} catch (InvalidLegsException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void unhingeJaw() {
		System.out.println("My mouth is wide open now!");
	}
	
	public void bite() {
		System.out.println("The snake bit its prey! Its venomous levels are now at " + --venom);
	}
}