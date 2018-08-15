package com.revature.fourPillars;

public abstract class Reptile extends Animal {

	protected boolean hasScales;
	protected boolean isAquatic;
	
	public void setHasScales(boolean set) {
		hasScales = set;
	}
	
	public boolean hasScales() {
		return hasScales;
	}
	
	public void setIsAquatic(boolean set) {
		isAquatic = set;
	}
	public boolean isAquatic() {
		return isAquatic;
	}

	public String genus() {
		return "Reptile";
	}
}
