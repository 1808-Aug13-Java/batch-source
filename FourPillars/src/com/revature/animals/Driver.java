package com.revature.animals;

public class Driver {

	public static void main(String[] args) {
		/*public Animals(int height, int weight, 
		int numberOfExtremities, int numberOfOffspring, 
		String type, int speed,
		int lifeSpan, int averageWeight)*/
		
		/*
		 * The following four lines of code show a two new Animals object being 
		 * created using an overloaded constructor where the parameters entered by
		 * the user are height,weight,numberofextremities,numberofoffspring, type of animal
		 * the speed the animal can reach, the lifespan of the animal, and the average weight
		 *  which is an example of polymorphism since we are implementing different functionality
		 *  of a method of the Animal class
		 * 
		 */
		Animals goldFish = new Animals(1,7,27,"Fish",777, 7,12);
		System.out.println(goldFish.toString());
		Animals crock = new Animals(5,2,123,"Reptiles",4, 23,300);
		System.out.println(crock.toString());
		System.out.println();
		
		//public Animals(int height, int weight, 
				//int numberOfExtremities, int numberOfOffspring, 
				//String type, int speed,
				//int lifeSpan, int averageWeight) 
		/*
		 * The following four lines of code show another two new Animals object being 
		 * created using a different overloaded constructor where the parameters entered by
		 * the user are height,weight,numberofextremities,numberofoffspring, type of animal
		 * the speed the animal can reach, the lifespan of the animal, and the average weight
		 *  which is an example of polymorphism since we are implementing different functionality
		 *  of a method of the Animal class
		 * 
		 */
		Animals crock2 = new Animals(6,2,22,25,"Reptiles",2, 100,300);
		System.out.println(crock2.toString());
		Animals cocktiel = new Animals(1,5,4,1,"Birds",5, 10,5);
		System.out.println(cocktiel.toString());
		System.out.println();
		
		/*
		 * The following calls the 7 parameter constructor again which checks for valid string type parameter
		 */
		Animals alien = new Animals(2,10,23,"Asari",1, 1000,120);
		System.out.println(alien.toString());
		//an custom handled exception occurs. which is handled by a try and catch clause in Animals
	}
}
