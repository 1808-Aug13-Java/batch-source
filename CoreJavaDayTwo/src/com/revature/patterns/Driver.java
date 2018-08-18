package com.revature.patterns;

public class Driver {

	
	
	public static void main(String[] args) {
		MySingleton instance1 = MySingleton.getInstance();
		MySingleton instance2 = MySingleton.getInstance();
		
		System.out.println(instance1.hashCode());
		System.out.println(instance2.hashCode());
		
		System.out.println("Setting val to 5");
		instance1.setValue(5);
		
		System.out.println("instance 2 val: " + instance2.getValue());
		
		AnimalFactory af = new AnimalFactory();
		af.getAnimal("sloth").makeNoise();
		af.getAnimal("whale").makeNoise();
		af.getAnimal("seal").makeNoise();
	}
	
	
}
