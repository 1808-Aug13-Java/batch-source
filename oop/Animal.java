package com.revature.oop;

//Abstract class has abstract maxSpeed method

abstract class Animal {
	
	public Animal() {
		super();
	}
	
	public void eat() {
		System.out.println("I eat meat!");
	}
// abstract method
	abstract void maxSpeed();
}
	
	
	
	