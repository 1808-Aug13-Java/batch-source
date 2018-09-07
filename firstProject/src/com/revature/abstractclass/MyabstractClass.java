package com.revature.abstractclass;

public abstract class MyabstractClass {
	
	//can include instance variables that aren't necessarily public or final
	
	// can include both concrete and abstract methods
	
	public abstract void myAbstractMethod();
	
	public void myConcreteMethod() {
		System.out.println("we can still provide some implementation ");
	}

}
