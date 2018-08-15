package com.revature.oop;

public class Mammal extends Animal{

	int jawBone;
	private boolean warmBlooded;
	private boolean liveBirth;
	
	public Mammal() {
		super();
	}

	public int getJawBone() {
		return jawBone;
	}

	public void setJawBone(int jawBone) {
		this.jawBone = jawBone;
	}

	public boolean isWarmBlooded() {
		return warmBlooded;
	}

	public void setWarmBlooded(boolean warmBlooded) {
		this.warmBlooded = warmBlooded;
	}

	public boolean isLiveBirth() {
		return liveBirth;
	}

	public void setLiveBirth(boolean liveBirth) {
		this.liveBirth = liveBirth;
	}
	
	
}
