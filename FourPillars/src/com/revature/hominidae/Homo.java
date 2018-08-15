package com.revature.hominidae;

public class Homo extends Homini {
	//population of Homo 
	protected static int population = 0; 
	protected String name; //personal name of the human e.g. John, Beatriz, Regis Philbin, etc. 
	protected String country; //where is he from?
	
	{
		bipedal = true;
		speech = true;
		tools = true;
		population +=1;
	}
	public Homo() {
		super();
	}

	public Homo(char sex) {
		super(sex);
	}
	public Homo(char sex, String name) {
		super(sex);
		this.name = name;
	}

	public static int getPopulation() {
		return population;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void walk() {
		System.out.println("(Homo) walking");
	}

}
