package com.revature.hominidae;

//Hominae (abstract), family containing families Gorillini and Homini
//*Gorillini - family containing the Great Apes family (gorillas & chimps)
//*Homini (abstract)- family containing families Australeopithecus and Homo
//*Australeopithecus - family containing species A. Africanus, A. Afarensis, etc. (not implemented)
//**Homo - family containing species Homo Sapiens and Homo Neanderthalis (not implemented)

public abstract class Homininae {
	protected boolean bipedal; //walks on two legs?
	protected boolean speech; //has speech (language)?
	protected boolean tools; //uses artificial (man-made) tools?
	protected int height; //let's say it's in centimeters
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

	public void setSex(char sex) throws SexException {
		//I wanted to put this in the constructor but couldn't figure how to do it
		//this method exists for demo purposes only
		//todo: add lower case transformation statement
		if(sex != 'm' && sex != 'f')
			throw new SexException("sex must be m or f");
		this.sex = sex;
	}

	public void setHeight(int height) throws HeightException {
		if(!(height > 0))
			throw new HeightException("height must be positive number");
		if(height > Integer.MAX_VALUE-1)
			throw new HeightException("height is too much");
		this.height = height;
	}
	
	public int getHeight() {
		return height;
	}

	public static int getPopulation() {
		return population;
	}
	
	//abstract because no such thing as a "Hominina(e)", it only exists in the mind
	public abstract void walk();
}

