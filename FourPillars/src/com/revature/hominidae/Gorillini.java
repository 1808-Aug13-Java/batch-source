package com.revature.hominidae;

public class Gorillini extends Homininae {
	//population of Gorillini 
	protected static int population=0;

	public Gorillini() {
		super();
		bipedal = false;
		speech = false;
		tools = false;
		population +=1;
	}	

	public Gorillini(char sex) {
		super(sex);
		bipedal = false;
		speech = false;
		tools = false;
		population +=1;
	}	
	public void walk() {
		System.out.println("(Gorillini) walking");
	}
	
	//hides method in Homininae
	public static int getPopulation() {
		return population;
	}
}
