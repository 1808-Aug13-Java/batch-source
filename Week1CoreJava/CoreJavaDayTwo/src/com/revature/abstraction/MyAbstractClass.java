package com.revature.abstraction;

public abstract class MyAbstractClass {
	// can have anything a class can have
	// plus abstract methods and vars
	// can include concrete and abstract methods
	
	public abstract void myAbstractMethod();
	
	public void myConcreteMethod() {
		System.out.println("We can still provide some "
				+ "implementation in our abstract class");
	}
}
