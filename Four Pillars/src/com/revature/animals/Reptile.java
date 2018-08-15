package com.revature.animals;

public class Reptile extends Animal {
	
	private boolean isAvian; //Some reptiles are more closely related to birds than other reptiles 
	private boolean isSaltwater;
	private boolean isNurturer; //Some reptiles take care of there young after they hatch for a limited time
	
	//Methods 
	
	public Reptile() {
		super();
	}
	public Reptile(int age, int numLegs, String species, String diet, String huntMethod, double hunger) {
		super(age, numLegs, species, diet, huntMethod, hunger);
	}
	
	//Setters and Getters 
	public boolean isAvian() {
		return isAvian;
	}
	
	public void setAvian(boolean isAvian) {
		this.isAvian = isAvian;
	}
	public boolean isSaltwater() {
		return isSaltwater;
	}
	public void setSaltwater(boolean isSaltwater) {
		this.isSaltwater = isSaltwater;
	}
	public boolean isNurturer() {
		return isNurturer;
	}
	public void setNurturer(boolean isNurturer) {
		this.isNurturer = isNurturer;
	}
	
	//Override HashCode 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (isAvian ? 1231 : 1237);
		result = prime * result + (isNurturer ? 1231 : 1237);
		result = prime * result + (isSaltwater ? 1231 : 1237);
		return result;
	}
	
	//Override Equals 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reptile other = (Reptile) obj;
		if (isAvian != other.isAvian)
			return false;
		if (isNurturer != other.isNurturer)
			return false;
		if (isSaltwater != other.isSaltwater)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Reptile [isAvian=" + isAvian + ", isSaltwater=" + isSaltwater + ", isNurturer=" + isNurturer + ", age="
				+ age + ", numLegs=" + numLegs + ", species=" + species + ", diet=" + diet + ", huntMethod="
				+ huntMethod + ", hunger=" + hunger + "]";
	}
	
	
	
	
	
	
}
