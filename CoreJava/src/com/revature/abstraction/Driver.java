package com.revature.abstraction;

public class Driver {

	public static void main(String[] args) {
		
		InterfaceImpl impl = new InterfaceImpl();
		impl.doSomething();
		impl.doSomethingElse();
		System.out.println(InterfaceA.MY_INT);
		System.out.println();
		
		impl.myConcreteMethod();
		impl.myAbstractMethod();

	}

}