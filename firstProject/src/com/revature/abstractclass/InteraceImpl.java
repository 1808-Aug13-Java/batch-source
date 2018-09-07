package com.revature.abstractclass;

public class InteraceImpl extends MyabstractClass implements InterfaceA, InterfaceB {
	
	@Override
	public void doSomething() {
		
	}

	@Override
	public void doSomethign() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doSomethingElse() {
		// TODO Auto-generated method stub
		InterfaceA.super.doSomethingElse();
	}

	public void myAbstractMethod() {
		// TODO Auto-generated method stub
		
	}

}
