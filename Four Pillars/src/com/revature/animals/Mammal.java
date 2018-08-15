package com.revature.animals;

public class Mammal extends Animal {
	
	//Only the largest mammals have placentas making them placentals 
	private boolean isPlacental;
	private String hairType; //All mammals have hair or fur 
	private boolean isRodent; //All rodent's are mammals but not all mammals are rodents
	
	//Methods
	
	public Mammal() {
		super();
	}

	public Mammal(int age, int numLegs, String species, String diet, String huntMethod, double hunger) {
		super(age, numLegs, species, diet, huntMethod, hunger);
	}

	public boolean isPlacental() {
		return isPlacental;
	}

	public void setPlacental(boolean isPlacental) {
		this.isPlacental = isPlacental;
	}

	public String getHairType() {
		return hairType;
	}

	public void setHairType(String hairType) {
		this.hairType = hairType;
	}

	public boolean getIsRodent() {
		return isRodent;
	}

	public void setIsRodent(boolean isRodent) {
		this.isRodent = isRodent;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((hairType == null) ? 0 : hairType.hashCode());
		result = prime * result + (isPlacental ? 1231 : 1237);
		result = prime * result + (isRodent ? 1231 : 1237);
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
		Mammal other = (Mammal) obj;
		if (hairType == null) {
			if (other.hairType != null)
				return false;
		} else if (!hairType.equals(other.hairType))
			return false;
		if (isPlacental != other.isPlacental)
			return false;
		if (isRodent != other.isRodent)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Mammal [isPlacental=" + isPlacental + ", hairType=" + hairType + ", isRodent=" + isRodent + ", age="
				+ age + ", numLegs=" + numLegs + ", species=" + species + ", diet=" + diet + ", huntMethod="
				+ huntMethod + ", hunger=" + hunger + "]";
	}

	
}
