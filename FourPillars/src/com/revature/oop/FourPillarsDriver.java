package com.revature.oop;

public class FourPillarsDriver {

	public static void main(String[] args) {
		Dog rex = new Dog(4, false, "Meow!");
		System.out.println("Your animal has: " + rex.getNumLegs() + " legs");
		System.out.println("Your animal can fly: " + rex.getFly());
		System.out.println("Your animal goes: " + rex.getSound());
		rex.setNumLegs(0);
		rex.fetch();
		System.out.println();
		
		Dog fido = new Dog();
		fido.setNumLegs(0);
		fido.setFly(true);
		fido.setSound("Woof!");
		System.out.println();
		
		Snake spunky = new Snake(0, false, "Hiss!");
		System.out.println("Your animal has: " + spunky.getNumLegs() + " legs");
		System.out.println("Your animal can fly: " + spunky.getFly());
		System.out.println("Your animal goes: " + spunky.getSound());
		spunky.unhingeJaw();
		spunky.bite();
		System.out.println();
		
		Snake khan = new Snake();
		khan.setNumLegs(5);
		khan.setFly(true);
		khan.setSound("Yodel!");
		System.out.println();
		
		Animal tweety = new Bird(2, true, "Cheep!"); //This is an example of Covariant types
		System.out.println("Your animal has: " + tweety.getNumLegs() + " legs");
		System.out.println("Your animal can fly: " + tweety.getFly());
		System.out.println("Your animal goes: " + tweety.getSound());
		((Bird) tweety).ascend(); //In order to use the Bird class items, I had to cast
		((Bird) tweety).descend();//the object 'tweety' as a bird 
		System.out.println();	  //Another example of Polymorphism
		
		Octopus oswald = new Octopus(8, false, "Wiggles!");
		System.out.println("Your animal has: " + oswald.getNumLegs() + " legs");
		System.out.println("Your animal can fly: " + oswald.getFly());
		System.out.println("Your animal goes: " + oswald.getSound());
		oswald.inkSac();
		
	}

}
