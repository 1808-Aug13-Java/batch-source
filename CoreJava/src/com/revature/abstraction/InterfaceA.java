package com.revature.abstraction;

/*
 * we can make an interface explicitly abstract 
 * but it wouldn't change anything as it, and all of its members are implicitly abstract
 */
public interface InterfaceA {

	//members of an interface are implicitly public
	
	//variables in an interface are public, static and final 
	int MY_INT = 5;

	void doSomething();
	
	default void doSomethingElse() {
		System.out.println("InterfaceA is doing something else");
	}
	
}
