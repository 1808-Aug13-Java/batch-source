package com.revature.oop;

public class Bird extends Animal {
	int height = 1;
	public Bird() { //This is an instance of a default having no parameters
		super(2, true, "Sqwuak!");//Because of this they have default values
	}
	
	public Bird(int numLegs, boolean fly, String sound) { //However if parameters are introduced
		super(numLegs, fly, sound);						  //This method is used instead
	}													  //Another example of Polymorphism
	
	public void setFly(boolean fly) {
		if(fly) {
			this.fly = fly;
		} else { 
			this.fly = fly;
		}
	}
	
	public void ascend() {
		System.out.println("The bird is now at " + ++height + " feet");
	}
	
	public void descend() {
		System.out.println("The bird is now at " + --height + " feet");
	}
}
