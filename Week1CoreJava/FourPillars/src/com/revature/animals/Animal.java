package com.revature.animals;

import com.revature.exceptions.NegativeAge;

public class Animal {
	// keeping our strings private while using getters/setters
	// is the idea of encapsulation aka information hiding
	private String name;
	private int numberOfExtremities;
	private boolean hasFur;
	private int age; 
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) throws NegativeAge {
		if(age < 0) {
			throw new NegativeAge();
		}
		this.age = age;
	}

	public Animal() {
		super();
	}
	
	public Animal(int age) throws NegativeAge {
		if(age < 0) {
			throw new NegativeAge();
		}
		this.age = age;
	}
	
	public Animal(String name) {
		this.name = name;
	}
	
	public Animal(String name, int numberOfExtremities) {
		this.name = name;
		this.numberOfExtremities = numberOfExtremities;
	}
	
	public Animal(String name, int numberOfExtremities, boolean hasFur) {
		this.name = name;
		this.numberOfExtremities = numberOfExtremities;
		this.hasFur = hasFur;
	}

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumberOfExtremities() {
		return numberOfExtremities;
	}
	public void setNumberOfExtremities(int numberOfExtremities) {
		this.numberOfExtremities = numberOfExtremities;
	}
	
	public boolean getHasFur() {
		return hasFur;
	}
	
	public void setHasFur(boolean hasFur) {
		this.hasFur = hasFur;
	}
	
	
}
