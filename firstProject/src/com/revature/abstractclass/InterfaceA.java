package com.revature.abstractclass;

/* We can make an interface explicitly abstract but it wouldn't change anything as it is, 
   and all of its members are implicitly abstract
*/
public interface InterfaceA {

	//members of an interface are implicitly public
	
	//variables in an interface are public, static, and final
	int MY_INT = 5;
	
	//methods are default public 
	
	void doSomething();
	
	default void doSomethingElse() {
		System.out.println("interfaceA is doing somthing else");
	}
}
