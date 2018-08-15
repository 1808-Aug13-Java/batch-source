package com.revature.taxonomy;

import com.revature.exceptions.TooManyLegsException;

//A class is Java's basic unit of encapsulation
//it binds variables and methods into an object that is safe
//from outside interference and misuse

abstract public class Animal {
	
	protected boolean vertabrate;
	protected boolean wings;
	protected int numLegs;
	
	
	public static void main(String[] args) {

		Animal dog1 = new Dog();
		//Superclass name = new subclass() is a covariant type
		//assignment which is an example of Polymorphism
		
		dog1.getNumLegs();
		dog1.hasWings();
		dog1.hasVertabrate();
		dog1.isSleeping();
		
		Animal bat = new Mammal();
		bat.setNumLegs(5);
		
		//Exception thrown
		try { if(bat.numLegs > 4)
			throw new TooManyLegsException();
			}
		catch(TooManyLegsException e) {
			e.printStackTrace();
		}
		
	}
	public Animal() {
		super();
	}
	
	abstract void isSleeping();
	
	public void getNumLegs() {
		System.out.println("This animal has " + this.numLegs +" legs.");
	}
	

	public void hasWings() {
		System.out.println("This animal has" + (wings != false ? "":" no") + " wings.");
	}
	
	public void hasVertabrate() {
		System.out.println("This animal has" + (vertabrate != false ? "":" no") + " vertabrate.");
	}

	public void setNumLegs(int numLegs) {
		this.numLegs = numLegs;
	}
	
	void setVertabrate(boolean vertabrate) {
		this.vertabrate = vertabrate;
	}
}
