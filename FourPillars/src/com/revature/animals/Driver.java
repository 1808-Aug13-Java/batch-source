package com.revature.animals;

import com.revature.exceptions.NegativeAge;
import com.revature.exceptions.NegativeTuskLength;

public class Driver {
	
	public static void main(String[] args) {
		Human jakob = new Human("jakob");
		jakob.walk();
		
		Animal animalWithFur = new Animal("Beary", 4, true);
		// covariant types are an example of polymorphic behavior
		Animal bear = new Mammal("Beary", true); 
		
		// another example of polymorphism among the super class and child class
		
		System.out.println(animalWithFur.getName());
		System.out.println("The bear's name is: " + bear.getName());
		
		// Handling exceptions
		
		Elephant timbo = new Elephant(40);
		timbo.setName("timbo");
		
		System.out.println(timbo.getName() + " has a tusk length of: " + timbo.getLengthOfTusks());
		
		try {
			timbo.setAge(21);
		} catch (NegativeAge e) {
			System.out.println("Age cannot be a negative number");
			e.printStackTrace();
		}
		
		try {
			timbo.setLengthOfTusks(50);
		} catch (NegativeTuskLength e) {
			System.out.println("Tusk length cannot be a negative number");
			e.printStackTrace();
		}
	}
	
}
