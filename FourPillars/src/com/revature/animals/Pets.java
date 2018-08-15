package com.revature.animals;

import java.util.Arrays;

import com.revature.exceptions.NonExistentPetException;

// This class is where abstraction shines. Implementation details (getters, setters, instantiation of
// various variables, etc.) are hidden. We can simply give our "Pets" certain properties 
// based on the information held in their class without worrying about the finer details
public class Pets {

	public static void main(String[] args) {
		
		// Create an instance of the Fish class
		Fish myFish = new Fish();
		try {
			// try to set the vocalizationType to "None" (since fish don't bark, meow, etc.)
			myFish.setVocalizationType("None");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Create an instance of the Cat class and set its properties
		// Here, we safely access these protected and private variables using setter methods
		// that were defined in the Cat class.
		Cat myCat = new Cat();
		myCat.setHasLegs(true);
		myCat.setVocalizationType("Meow!");
		myCat.setMovementStyle("Walk");
		myCat.setNumberOfLegs(4);
		myCat.setColorOfFur("Orange");
		myCat.setLikesDogs(false);
		System.out.println(myCat);
		
		// Create an instance of the Dog class and set its properties
		// Here, we safely access these protected and private variables using setter methods
		// that were defined in the Dog class.
		Dog myDog = new Dog();
		myDog.setHasLegs(true);
		myDog.setVocalizationType("Meow!");
		myDog.setMovementStyle("Walk");
		myDog.setNumberOfLegs(4);
		myDog.setFavoriteToy("Stuffed Bear");
		myDog.setBreed("Golden Retriever");
		System.out.println(myDog);
		
		// Create an array of size 3
		String[] myPets = {"Fish", "Cat", "Dog"};
		
		// This is essentially a custom unchecked exception over an ArrayIndexOutOfBoundsException
		// when attempting access a nonexistent member of myPets
		try {
			myPets[10] = "Zebra";
			throw new NonExistentPetException("Woops! Looks like you're trying to access a pet that doesn't exist.");
			
		} catch (Exception e) {
			// simply output the standard stacktrace for the exception to the console
			e.printStackTrace();
		} finally {
			System.out.println(Arrays.toString(myPets));
		}
	}
}
