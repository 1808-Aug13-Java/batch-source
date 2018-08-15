package com.revature.animalbase;

public class Driver {

	public static void main(String[] args) {
		//Demonstrates covariant types since AnglerFish is a subclass of Fish
		//which is a subclass of Animal.
		Animal fishy = new AnglerFish(0, false, 10, "Angles, the fish,");
		Fish fishy2 = new AnglerFish(0, false, 7, "Fish McFish");
		fishy.walk();
		fishy2.walk(10);
		//this will throw an exception.
		fishy.setNumberLegs(-2);
		
		Antelope anty = new Antelope(4,true,55,"Anty, the Antelope");
		
		anty.walk();
		
	}
}
