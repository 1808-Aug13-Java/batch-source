package com.revature.hominidae;
//
//Hominae (abstract), family containing families Gorillini and Homini
//*Gorillini - family containing the Great Apes family (gorillas & chimps)
//*Homini (abstract)- family containing families Australeopithecus and Homo
//*Australeopithecus - family containing species A. Africanus, A. Afarensis, etc. (not implemented)
//**Homo - family containing species Homo Sapiens and Homo Neanderthalis (not implemented)

//default access modifiers for all
public abstract class Homininae {
	protected boolean bipedal; //walks on two legs?
	protected boolean speech; //has speech (language)?
	protected boolean tools; //uses artificial tools?
	protected float height; 
	protected static int population = 0; //how members of each family's population
	protected char sex; //accepts 'm' or 'f'; could've used boolean but decided to not 	
	
	public Homininae() {
		population +=1;
	}
	
	public Homininae(char sex) {
		this.sex = sex;
	}
	public void setBipedal(boolean bipedal) {
		this.bipedal = bipedal;
	}	
	public boolean getBipedal() {
		return this.bipedal;
	}
	
	public void setSpeech(boolean speech) { 
		this.speech = speech;
	}		
	public boolean getSpeech() {
		return this.speech;
	}
	
	public void setTools(boolean tools) {
		this.tools = tools;
	}		
	public boolean getTools() {
		return this.tools;
	}
	
	public char getSex() {
		return sex;
	}	

	public void setSex(char sex) {
		this.sex = sex;
	}
	public static int getPopulation() {
		return population;
	}
	
	public abstract void walk();
}

