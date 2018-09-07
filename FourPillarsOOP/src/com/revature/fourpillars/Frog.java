package com.revature.fourpillars;

import com.revature.exceptions.AnimalException;

/*
 * This class makes use of an Abstract class and an Implementation displaying two of the pillars on OOP.
 * 
 * Having extended the abstract class Animal provides inheritance which gives this class, Frog, the methods 
 * and variables of the abstract class, as well as implementing the abstract methods outlined in the abstract
 * class which acts as a basic blue print of all subclasses of the Animal class. This shows us Inheritance.
 * 
 * By implementing the interface Amphibian this class must define the methods outlined in the interface. This allows 
 * this class to inherit from more than one class.
 */
public class Frog extends Animal implements Amphibian{
	
	private int numLegs;
	private int vertJump;
	private int horizJump;
	private boolean flyCaught;
	private int numFliesAte;
	private String classification;
	
	public Frog() {
		super();//inherited from Amphibian
	}
	
	public Frog(int numLegs, int vertJump, int horizJump, boolean flyCaught, int numFliesAte) {
		super("small", true, false); ////inherited from Animal
		this.numLegs = numLegs;
		this.vertJump = vertJump;
		this.horizJump = horizJump;
		this.flyCaught = flyCaught;
		this.numFliesAte = numFliesAte;
		setClassification();
	}
	//inherited from Amphibian
	@Override
	public void setClassification() {
		this.classification = Amphibian.classification;
		
	}
	//inherited from Amphibian
	@Override
	public void breathUnderWater() {
		System.out.println("As an amphibian this creature breaths through its skin, and can breath in water or out of it.");
		
	}
	//inherited from Amphibian
	@Override
	public void setBodyTemp() {
		System.out.println("As an amphibian this creature does not regulate its body temperature. "
				+ "Its temperature depends on its enviroment.");
		
	}

	public String getClassification() {
		return classification;
	}
	
	public int getNumLegs() {
		return numLegs;
	}

	public void setNumLegs(int numLegs) {
		if(numLegs > 4 || numLegs < 0) {
			try {
				throw new AnimalException("The number of legs for a frog must be between 0 and 4.");
			} catch (AnimalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("Frog has  " + numLegs + " number of legs.");
			this.numLegs = numLegs;	
		}
		
	}

	public int getVertJump() {
		return vertJump;
	}

	public void setVertJump(int vertJump) {
		this.vertJump = vertJump;
	}

	public int getHorizJump() {
		return horizJump;
	}

	public void setHorizJump(int horizJump) {
		this.horizJump = horizJump;
	}

	public boolean isFlyCaught() {
		return flyCaught;
	}

	public void setFlyCaught(boolean flyCaught) {
		this.flyCaught = flyCaught;
	}

	public int getNumFliesAte() {
		return numFliesAte;
	}

	public void setNumFliesAte(int numFliesAte) {
		this.numFliesAte = numFliesAte;
	}


	@Override
	public String toString() {
		return "Frog [numLegs=" + numLegs + ", vertJump=" + vertJump + ", horizJump=" + horizJump + ", flyCaught="
				+ flyCaught + ", numFliesAte=" + numFliesAte + ", classification=" + classification + ", size=" + size
				+ ", isPredator=" + isPredator + ", isGatherer=" + isGatherer + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((classification == null) ? 0 : classification.hashCode());
		result = prime * result + (flyCaught ? 1231 : 1237);
		result = prime * result + horizJump;
		result = prime * result + numFliesAte;
		result = prime * result + numLegs;
		result = prime * result + vertJump;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Frog other = (Frog) obj;
		if (classification == null) {
			if (other.classification != null)
				return false;
		} else if (!classification.equals(other.classification))
			return false;
		if (flyCaught != other.flyCaught)
			return false;
		if (horizJump != other.horizJump)
			return false;
		if (numFliesAte != other.numFliesAte)
			return false;
		if (numLegs != other.numLegs)
			return false;
		if (vertJump != other.vertJump)
			return false;
		return true;
	}	

}
