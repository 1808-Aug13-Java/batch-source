package com.revature.species;

public class AnimalDriver {

	public static void main(String[] args) {
		
		Animal an1 = new Animal();
		Animal an2 = new Animal();
		
		System.out.println(an1);
		System.out.println(an2);
		System.out.println(an1.equals(an2)); // uses equals method I defined in Animal class
			
		System.out.println("Declaring an animal object:");
		Animal myAnimal = new Animal();
		myAnimal.setSpeed(10);
		myAnimal.setSpeed(20);
		System.out.println();
		
		System.out.println("Declaring a bat object:");
		Bat freeTailed = new Bat();
		freeTailed.setSonarFreq(110);
		freeTailed.setSpeed(100);
		freeTailed.setSpeed(200); // exception
		freeTailed.setFurColor("Red");
		freeTailed.setWingspan(50);
		System.out.println("some freeTailed info: sonarFreq: " + freeTailed.getSonarFreq() + " speed: " + freeTailed.getSpeed() + " fur color: " + freeTailed.getFurColor());
		System.out.println();
		
		System.out.println("Declaring a dolphin object:");
		Dolphin bottlenose = new Dolphin();
		bottlenose.setHasWings(true);
		bottlenose.setLivesInWater(true);
		bottlenose.setDorsalFinCurve(50);
		//bottlenose.setShadeOfGray("black"); // exception
		bottlenose.setShadeOfGray("gray");
		System.out.println("some bottlenose info: " + bottlenose.getShadeOfGray() + " has wings?: " + bottlenose.isHasWings() + " Dorsal fin curve: " + bottlenose.getDorsalFinCurve());
		System.out.println();
		
		System.out.println("Declaring a elephant object:");
		Elephant african = new Elephant();
		african.setSpeed(5);
		african.setLengthOfTrunk(10);
		african.setLivesInWater(false);
		System.out.println("some african Elephant info: speed: " + african.getSpeed() + " length of trunk: " + " lives in water?: " + african.isLivesInWater());
		System.out.println();

	}

}
