package com.revature.fourpillars;

import com.revature.exceptions.NegativeAgeException;
import com.revature.exceptions.incorrectCharacterInNameException;;

// Inheritance: using the Celebrity class as a super class 
// promotes code reusability because the subclasses
// like Politician, Athlete, and Musician don't have to define
// each thing individually
public abstract class Celebrity {
	// Encapsulation: making the instance fields protected so that only the class 
	// itself, package, and subclasses can see them
	protected String profession;
	protected String name;
	protected int age;
	
	// Default constructor
	public Celebrity() {
		profession = "";
		name = "";
		age = 0;
	}
	
	public Celebrity(String profession, String name, int age) {
		// making the use of the 'this' keyword to avoid variable shadowing
		this.profession = profession;
		this.name = name;
		this.age = age;
	}
	
	// Applying encapsulation through the use of getters and setters
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		boolean incorrectCharacterInName = !name.matches("^.*[^a-zA-Z'!].*$");
		if (incorrectCharacterInName) { // checked exception
			try {
				throw new incorrectCharacterInNameException("That is not a name!");
			} catch (incorrectCharacterInNameException e) {
				e.printStackTrace();
			}
		}
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		if (age < 0) { // unchecked exception: 
			throw new NegativeAgeException("The age can't be negative");
		}
		this.age = age;
	}

	// overriding Object's equals method as an example of run-time polymorphism
	// the method to be used will be defined at runtime
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Celebrity other = (Celebrity) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (profession == null) {
			if (other.profession != null)
				return false;
		} else if (!profession.equals(other.profession))
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((profession == null) ? 0 : profession.hashCode());
		return result;
	}
}