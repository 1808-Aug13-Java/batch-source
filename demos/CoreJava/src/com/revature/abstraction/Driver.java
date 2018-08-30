package com.revature.abstraction;

public class Driver {

	public static void main(String[] args) {
		
		InterfaceImpl impl = new InterfaceImpl();
		impl.doSomething();
		System.out.println(impl.getClass().getName());
		impl.doSomethingElse();
		System.out.println(InterfaceA.MY_INT);
		System.out.println();
		
		impl.myConcreteMethod();
		impl.myAbstractMethod();
		
		//cannot instantiate MyAbstractClass, even when it has no abstract methods
		//MyAbstractClass ac = new MyAbstractClass();

	}

}
