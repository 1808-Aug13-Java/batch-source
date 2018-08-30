package com.revature.abstraction;

public interface InterfaceB {
	
	void doSomething();
	
	default void doSomethingElse() {
		System.out.println("InterfaceB doing something else..");
	}

}
