package com.revature.drivers;

import com.revature.animals.Animal;
import com.revature.animals.Dog;
import com.revature.animals.Gorilla;
import com.revature.animals.Lion;
import com.revature.animals.Snake;

public class AnimalDriver {

	public static void main(String[] args) {
		
		// instantiates all Animal objects
		Lion leo = new Lion("male");
		Lion leona = new Lion("female");
		Lion l = new Lion("car"); // throws a NonbinaryGenderException (checked)
		Gorilla harambe = new Gorilla();
		Dog spot = new Dog();
		Snake sam = new Snake();
		
		System.out.println("Leo the lion's turn.");
		
		// the following method calls use the "interfaces" of the Lion, Gorilla, Dog and Snake classes.
		// the AnimalDriver class does not need to know the implementation details of these methods.
		// this demonstrates Abstraction (Pillar of OOP 4)
		leo.walk();
		leo.makeNoise();
		System.out.println("toString: " + leo.toString());
		
		System.out.println();
		System.out.println("Leona the lioness's turn.");
		
		leona.walk();
		System.out.println("toString: " + leona.toString());
		
		System.out.println();
		System.out.println("Harambe the gorilla's turn.");
		
		harambe.makeNoise();
		harambe.attack();
		harambe.giveBanana();
		harambe.attack();
		harambe.die();
		System.out.println("toString: " + harambe.toString());
		
		System.out.println();
		System.out.println("Spot the dog's turn.");

		spot.makeNoise();
		spot.attack();
		spot.giveDogABone();
		spot.attack();
		System.out.println("toString: " + spot.toString());
		
		System.out.println();
		System.out.println("Sam the snake's turn.");
		
		sam.slither();
		sam.makeNoise();
		sam.attack();
		System.out.println("toString: " + sam.toString());
	
		System.out.println();
		// STATIC_ANIMAL_DECLARATION belongs to the Animal class
		System.out.println("Static Animal constant: " + Animal.STATIC_ANIMAL_DECLARATION);
		
		sam.killProgram(); // throws an UncheckedAnimalException
		
	}

}
