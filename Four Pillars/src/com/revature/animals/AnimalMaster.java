package com.revature.animals;

//This class will run our application utilizing all of the animals functionality 
public class AnimalMaster {

	public static void main(String[] args) {
		
		
	//Instantiate our zoo
	Bird flamingo = new Bird(3, 2, "flamingus", "fish", "Nose Dive", 0.5); 
	Mammal monkey = new Mammal(12, 2, "primate", "Bannanas", "Swipe", 1.0);
	Reptile crocidile = new Reptile(7, 4, "crocidilian", "MEAT", "Snapping Jaw", 0.6);
	
	//Set variables
	flamingo.setCanFly(true); //Flamingos can indeed fly though mostly at night
	flamingo.setWingSpan(150); //Flamingos have a whopping 150 cm wingspan 
	monkey.setIsRodent(false); //Monkey's can be annoying but they're not rodents!
	monkey.setHairType("Lumberjack Hairy"); //They're fuzzy'
	monkey.setPlacental(true); //Very similar to humans afterall
	crocidile.setAvian(true); //Crocidiles are actually closer related to birds than common reptiles
	crocidile.setSaltwater(false); //This crocidile is not a salt water giant
	crocidile.setNurturer(true); //This crocidile takes care of its babies once they hatch
	
	//Let's meet the zoo 
	System.out.println("Meet Felicia the Flamingo: " + flamingo.toString());
	System.out.println("Meet Maurice the Monkey: " + monkey.toString());
	System.out.println("Meet Chris the Crocidile: " + crocidile.toString());
	System.out.println();
	
	//Let's feed them 
	System.out.println("Let's feed Felicia a juicy fish!");
	flamingo.eatFood(0.3);
	System.out.println("*" + flamingo.huntMethod + "*");
	System.out.println("Now let's feed Maurice an awesome bannana!");
	monkey.eatFood(0.5);
	System.out.println("*" + monkey.huntMethod + "*");
	System.out.println("Finally let's give chris uhm.... Meat......");
	crocidile.eatFood(1.0);
	System.out.println("*" +crocidile.huntMethod + "*");
	
	//Now let's see how hungry they are
	System.out.println("Good! Let's see how hungry they are now!");
	System.out.println("Felicia's Hunger: " + flamingo.checkHunger() + "%");
	System.out.println("Maurice's Hunger: " + monkey.checkHunger() + "%");
	System.out.println("Chris's Hunger: " + crocidile.checkHunger() + "%");
	
	System.out.println("Thanks for joining us!");
	
	}

}
