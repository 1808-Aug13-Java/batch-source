package com.revature.abstraction;

public abstract class MyAbstractClass {
//	can include instance variables
//	that aren't necessarily public or final
	
//	can include both concrete and abstract methods
	
	public abstract void myAbstractMethod();
	
	public void myConcreteMethod() {
		System.out.println("We can still provide some implementation in our abstract class.");
	}
	
//	cannot instantiate MyAbstractClass, even if it has no abstract methods
	
}
