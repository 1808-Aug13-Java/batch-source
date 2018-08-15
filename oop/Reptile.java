package com.revature.oop;

public class Reptile extends Animal {
	
	private boolean coldBlooded;
	private boolean hasScales;
	private boolean tearFood;
	
	public Reptile() {
		super();
	}
	
	public boolean isTearFood() {
		return tearFood;
	}

	public void setTearFood(boolean tearFood) {
		this.tearFood = tearFood;
	}

	public boolean isColdBlooded() {
		return coldBlooded;
	}

	public void setColdBlooded(boolean coldBlooded) {
		this.coldBlooded = coldBlooded;
	}

	public boolean isHasScales() {
		return hasScales;
	}

	public void setHasScales(boolean hasScales) {
		this.hasScales = hasScales;
	}
	

}
