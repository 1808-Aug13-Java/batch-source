package com.revature.animalbase;

import com.revature.exceptions.NegativeLegsException;

public class Fish extends Animal {
	public Fish() {
		super();
	}

	public Fish(int legs, boolean fur, int speed, String name) {
		super(legs, fur, speed, name);
	}
	public void walk() {
		System.out.println("The "+name+" swims forth!");
	}
	//Demonstrates method overloading.
	public void walk(int speed) {
		System.out.println("The "+name+" swims at a speed of "+speed);
	}
	//Here I use the concept of polymorphism to simply
	//Override the setNumberLegs method to give a different 
	//message when the exception is thrown.
	@Override
	public void setNumberLegs(int numberLegs) {
		if(numberLegs<0) {
			try {
				throw new NegativeLegsException("Fish typically have Zero legs.");
			}catch (NegativeLegsException e) {
				e.printStackTrace();
			}
		}else {
			this.numberLegs = numberLegs;
		}
	}
}
