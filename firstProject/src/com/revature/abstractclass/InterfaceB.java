package com.revature.abstractclass;

public interface InterfaceB {

	void doSomethign();
	
	default void doSomethingElse() {
		System.out.println("interfaceA is doing somthing else");
	}
}
