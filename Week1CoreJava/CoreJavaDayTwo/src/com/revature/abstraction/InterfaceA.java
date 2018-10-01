package com.revature.abstraction;


// implicitly abstract, but can be explicitly abstract
// all members are implicitly abstract
public interface InterfaceA {
	
	// members of an interface are implicitly public static final
	
	// variables in an interface
	int MY_INT = 5;
	
	
	// methods default public
	void doSomething(); // just our method signature
	
	default void doSomethingElse() {
		System.out.println("InterfaceA is doing something else");
	}
	
}
