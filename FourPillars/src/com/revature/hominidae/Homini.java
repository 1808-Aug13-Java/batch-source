package com.revature.hominidae;

public abstract class Homini extends Homininae {
	//population of Homini and all subclasses (Australeopithecus & Homo)
	protected static int population = 0; 
	public Homini() {
		super();
		population +=1;
	}	

	public Homini(char sex) {
		this.sex = sex;
	}

	public static int getPopulation() {
		return population;
	}


}
