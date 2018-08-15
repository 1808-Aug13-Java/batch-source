package com.revature.hominidae;

public class Australeopithecus extends Homini {
	//population of Australeopithecus 
	protected static int population = 0; 
	
	//initializer block for copying into multiple constructors
	{
		bipedal = true;
		speech = false;
		tools = false;
		population +=1;
	}
	public Australeopithecus() {
		super();
	}
	public void walk() {
		System.out.println("(Australeopithecus) walking");
	}

	public static int getPopulation() {
		return population;
	}
}
