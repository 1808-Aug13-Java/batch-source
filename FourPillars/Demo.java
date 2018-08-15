package com.revature.main;
import com.revature.hominidae.*;

public class Demo {
	//print method wrapper for readability
	public static void print(Object obj) {
		System.out.println(obj);
	}
	public static void main(String[] args) {
		
		//POLYMORPHISM: putting objects into superclass reference
		//3 Gorrillini
		Homininae gorilla = new Gorillini();
		Homininae gorilla2 = new Gorillini();
		Homininae gorilla3 = new Gorillini();

		//5 Homo
		Homininae sapiens = new Homo('m');
		Homininae sapiens2 = new Homo('f', "Haydee Orellana Farias");
		Homininae neanderthalis = new Homo();
		Homininae habilis = new Homo();
		Homininae erectus = new Homo();

		//2 Australeopithecus
		Homininae africanus = new Australeopithecus();
		Homininae afarensis = new Australeopithecus();
		
		print("Homininae population: " + Homininae.getPopulation());
		print("Gorillini population: " + Gorillini.getPopulation());
		print("Homini population: " + Homini.getPopulation());
		print("Homo population: " + Homo.getPopulation());
		print("Australeopithecus population: " + Australeopithecus.getPopulation());

		print(((Homo)sapiens2).getName()); //cast Homo
		print(sapiens2.getSex());



	}
}
