package com.revature.abstraction;

public class InterfaceImpl extends MyAbstractClass implements InterfaceA, InterfaceB {

	@Override
	public void doSomething() {
		System.out.println("doing something..");		
	}
	
	@Override
	public void doSomethingElse() {
		//System.out.println("third implementation");
		InterfaceA.super.doSomethingElse();
	}

	@Override
	public void myAbstractMethod() {
		System.out.println("Not so abstract after all");	
	}

}
