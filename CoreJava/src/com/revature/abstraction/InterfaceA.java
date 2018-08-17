package com.revature.abstraction;

/* we can make an interace explicitly abstract 
 * but it wouldn't change anything as it, and all of its
 * members are implicitly abstract 
 */
public interface InterfaceA {
	
	// members of an interface are implicitly public
	
	// variables in an interface are public, static, and final (cannot be reassigned)
	int MY_INT = 5;
	// naming convention for interface variables is all capitals and underscore(s)
	
	void doSomething();
	
	default void doSomethingElse() {
		System.out.println("Interface A is doing something else.");
	}
	
}
