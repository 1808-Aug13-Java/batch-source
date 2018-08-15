package com.revature.main;
import com.revature.hominidae.*;
public class Demo {
	//print method wrapper for readability
	public static void print(Object obj) {
		System.out.println(obj);
	}

	//checks if object of type Homininae belongs to abstract class Homini
	//no purpose to this method other than it demonstrates checked Exception
	public static void checkedExceptionDemo(Homininae h) throws TypeException {
		if(!(h instanceof Homini))
			throw new TypeException("wrong kind of hominin");
	}
	public static void main(String[] args) {
		
		//POLYMORPHISM: putting objects into superclass reference
		//3 Gorrillini
		Homininae gorilla = new Gorillini();
		Homininae gorilla2 = new Gorillini();
		Homininae gorilla3 = new Gorillini();
		//Homininae sapiens2;
		Homininae sapiens2 = new Homo('f', "Haydee Orellana Farias");
		Homininae neanderthalis = new Homo();
		Homininae habilis = new Homo();
		Homininae erectus = new Homo();

		try {
			checkedExceptionDemo((Gorillini)gorilla);
		}
		catch(TypeException e) {
			e.printStackTrace();
		}
		try {
			sapiens2.setSex('x'); //set bogus exception
		}
		catch(SexException e) {
			e.printStackTrace();
		}

		try {
			sapiens2.setHeight(0);
		}
		catch(HeightException e) {
			e.printStackTrace();
		}

		//2 Australeopithecus
		Homininae africanus = new Australeopithecus();
		Homininae afarensis = new Australeopithecus();
			
		print("Homininae population: " + Homininae.getPopulation());
		print("Gorillini population: " + Gorillini.getPopulation());
		print("Homini population: " + Homini.getPopulation());
		print("Homo population: " + Homo.getPopulation());
		print("Australeopithecus population: " + Australeopithecus.getPopulation());

		//print(((Homo)sapiens2).getName()); //cast Homo
		print(sapiens2.getSex());



	}
}
