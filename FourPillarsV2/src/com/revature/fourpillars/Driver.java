package com.revature.fourpillars;

import com.revature.customexceptions.CustomCheckedException;

//This tested my methods and my exceptions
public class Driver {
	public static void main(String[] args) throws CustomCheckedException {
		
		Seal seal1 = new Seal();
		seal1.setNumLegs(-1);
		seal1.setNumLegs(0);
		System.out.println("Number of seal legs: " + seal1.getNumLegs());
		seal1.setNumFlippers(-1);
		seal1.setNumFlippers(2);
		System.out.println("Number of seal flippers: " + seal1.getNumFlippers());
		System.out.print("What does a seal say? ");
		seal1.speak();
		System.out.print("How does a seal move? ");
		seal1.move();
		seal1.setAlive(true);
		System.out.println("Seals are living creatures: " + seal1.isAlive());
		
		System.out.println();
		
		Cat cat1 = new Cat();
		cat1.setNumLegs(-1);
		cat1.setNumLegs(4);
		System.out.println("Number of cat legs: " + cat1.getNumLegs());
		System.out.print("What does a cat say? ");
		cat1.speak();
		System.out.print("How does a cat move? ");
		cat1.move();
		cat1.setAlive(true);
		System.out.println("Cats are living creatures: " + cat1.isAlive());
		
		System.out.println();
		
		Virus virus1 = new Virus();
		virus1.setNumLegs(0);
		System.out.println("Number of virus legs: " + virus1.getNumLegs());
		System.out.print("What does a virus say? ");
		virus1.speak();
		System.out.print("How does a virus move? ");
		virus1.move();
		virus1.setAlive(false);
		System.out.println("Viruses are living creatures: " + virus1.isAlive());
		
		System.out.println();
		
		Crow crow1 = new Crow();
		crow1.setNumLegs(2);
		System.out.println("Number of crow legs: " + crow1.getNumLegs());
		System.out.print("What does a crow say? ");
		crow1.speak();
		System.out.print("How does a crow move? ");
		crow1.move();
		crow1.setAlive(true);
		System.out.println("Viruses are living creatures: " + crow1.isAlive());
	}	
}
