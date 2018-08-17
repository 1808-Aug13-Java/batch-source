package com.revature.oop;


public class Dog extends Animal{
	public Dog() {
		/*numLegs = 4;
		fly = false;
		sound = "Woof!";*/
		super(4, false, "Woof!");
	}
	
	public Dog(int numLegs, boolean fly, String sound) {
		super(numLegs, fly, sound);
	}
	
	public void fetch() {
		System.out.println("Go get the ball!");
	}
}