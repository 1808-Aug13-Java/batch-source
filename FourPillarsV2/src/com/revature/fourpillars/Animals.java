package com.revature.fourpillars;

import com.revature.customexceptions.CustomUncheckedException;

//Using an abstract class is an example of abstraction because I put the details of WHAT an animal's state or  
//behavior may be, but the details of HOW we implement that code are in the subclasses. Using an abstract class
//as a parent class and having subclasses inherit from it is an example of inheritance as well. Additionally,
//method overriding from the abstract parent to the various child classes is an example of polymorphism in use.
//Finally, encapsulation is evident by the use of the protected members, which are accessible only to classes in the
//package, and public methods/method signatures, accessible anywhere. 

public abstract class Animals {

	public Animals() {
		super();
	}
	
	protected int numLegs;//protected access is part of encapsulation
	protected boolean isAlive;
	
	 Animals(int numLegs, boolean isAlive) {
		super();
		this.numLegs = numLegs;
		this.isAlive = isAlive;
	}

	public abstract int getNumLegs();//public access to getters/setters is part of encapsulation

	public abstract void setNumLegs(int numLegs) throws CustomUncheckedException;//custom unchecked exception

	public abstract boolean isAlive();

	public abstract void setAlive(boolean isAlive);

	public abstract void speak();
	
	public abstract void move();

}
