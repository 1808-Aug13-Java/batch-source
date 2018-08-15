package com.revature.henry;

public class Driver {

	public static void main(String[] args) throws AnimalMassException{
		Tarantula tarantula1 = new Tarantula("Tarantula","Carnivore",85); 	//Creating tarantula
		tarantula1.animalname();											//Method printing animal's name
		tarantula1.animaldiet();											//Method printing animal's diet
		tarantula1.animalmass();											//Method printing animal's mass
		System.out.println(tarantula1.toString());
		
		Duck duck1 = new Duck("Duck","Omnivore",500);
		duck1.animalname();
		duck1.animaldiet();
		duck1.animalmass();
		System.out.println(duck1.toString());
		
		Giraffe giraffe1 = new Giraffe("Giraffe","Herbivore",1192000);
		giraffe1.animalname();
		giraffe1.animaldiet();
		giraffe1.animalmass();
		System.out.println(giraffe1.toString());

		Rhino rhino1 = new Rhino("Rhino","Herbivore",1600000);
		rhino1.animalname();
		rhino1.animaldiet();
		rhino1.animalmass();
		System.out.println(rhino1.toString());

	}

}
