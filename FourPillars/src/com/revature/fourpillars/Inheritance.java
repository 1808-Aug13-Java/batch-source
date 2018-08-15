package com.revature.fourpillars;
import java.io.*;
abstract public class Inheritance {
	
	
	
	public static void makeTalk(Animal input){
		input.speak();
	}
	public static void main(String[] args) throws IOException{
		Animal anAnimal = new Animal();
		
		Bear bear = new Bear();
		Eagle eagle = new Eagle();
		Dog dog = new Dog();
		System.out.println("Make the Animal object speak");
		makeTalk(anAnimal);
		System.out.println("Make the Bear object speak");
		makeTalk(bear);
		System.out.println("Make the Eagle object speak");
		makeTalk(eagle);
		System.out.println("Make the Dog object speak");
		makeTalk(dog);
		
		
		try {
			//bear.setNumberOfLegs(-1);
			bear.setNumberOfLegs(4);
			dog.setNumberOfLegs(2);
			eagle.setNumberOfLegs(2);
		}
		catch(NegativeNumberOfLegsException ex){
			System.err.print(ex);
		}
		finally {
			
		}
		 
		
		
		
		System.out.println("Bear has " + bear.getNumberOfLegs() + " legs");
		System.out.println("Dog has " + dog.getNumberOfLegs() + " legs");
		System.out.println("Eagle has " + eagle.getNumberOfLegs() + " legs");
		
	
	FileReader file = new FileReader("C:\\test\\a.txt");
    BufferedReader fileInput = new BufferedReader(file);
     
    // Print first 3 lines of file "C:\test\a.txt"
    for (int counter = 0; counter < 3; counter++) 
        System.out.println(fileInput.readLine());
     
    fileInput.close();

}
}
