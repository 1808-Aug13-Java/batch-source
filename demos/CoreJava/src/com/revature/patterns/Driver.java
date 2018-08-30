package com.revature.patterns;

public class Driver {

	public static void main(String[] args) {
		MySingleton instance1 = MySingleton.getInstance();
		MySingleton instance2 = MySingleton.getInstance();
		System.out.println("Instance 1 hashcode: "+ instance1.hashCode());
		System.out.println("Instance 2 hashcode: "+instance2.hashCode());
		System.out.println();
		
		System.out.println("Setting instance 1's value to 5");
		instance1.setValue(5);
		System.out.println("Getting instance 2's value: "+instance2.getValue());
		System.out.println();
		
		AnimalFactory af = new AnimalFactory();
		Animal a1 = af.getAnimal("seal");
		Animal a2 = af.getAnimal("sloth");
		Animal a3 = af.getAnimal("whale");
		a1.makeNoise();
		a2.makeNoise();
		a3.makeNoise();
		
	}

}
