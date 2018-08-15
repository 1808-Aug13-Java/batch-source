package com.revature.animals;

import java.util.ArrayList;
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
		
		// Create an array of size 3 with all of our pets + one we want, but can't have
		// This final pet will lead to our custom unchecked exception
		ArrayList<String> myPets = new ArrayList<String>();
		String petFish = "Fish";
		String petCat = "Cat";
		String petDog = "Dog";
		String petZebra = "Zebra";
		
		myPets.add(petFish);
		myPets.add(petCat);
		myPets.add(petDog);
		myPets.add(petZebra);
		
		// Unchecked exception thrown if size of myPets > 3
		if (myPets.size() > 3) {
			throw new NonExistentPetException("Oops! We can only have 3 pets at a time.");
		}
	}
}
