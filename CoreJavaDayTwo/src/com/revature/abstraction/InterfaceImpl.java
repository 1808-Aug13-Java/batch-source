package com.revature.abstraction;

public class InterfaceImpl extends MyAbstractClass implements InterfaceA, InterfaceB  {

	@Override
	public void doSomething() {
		System.out.println("Doing something");
	}
	
	@Override
	public void doSomethingElse() {
		//System.out.println("InterfaceImpl class doing something else...");
		InterfaceA.super.doSomethingElse();
	}

	@Override
	public void myAbstractMethod() {
		System.out.println("Not so abstract after all");
	}
	
	public void printEach(int ...args) {
		for(int i: args) {
			System.out.println(i);
		}
	}

}