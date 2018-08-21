package com.revature.animals;

public class Human extends Mammal {

	public Human(String name, boolean hasFur) {
		super(name, hasFur);
	}


	public Human(String name) {
		super(name);
	}


	public Human() {
		super();
	}
	
	
	public void walk() {
		// by using the walk method of the Human class
		// the user of the class no longer has to worry about 
		// the implementation details of walking but just that the Human will walk
		// this is abstraction at work
		System.out.println("The human walked");
	}

}