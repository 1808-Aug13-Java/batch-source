package com.revature.beans;

public class BearAutowiringByType extends Bear {

	// uses setter injection based on the name of the setter method
	// corresponding to the type of any of our beans
	@Override
	public void setCave(Cave cave) {
		this.cave = cave;
	}

	
	@Override
	public String toString() {
		return "BearAutowiringByType [id=" + id + ", name=" + name + ", cave=" + cave + ", getId()=" + getId()
				+ ", getName()=" + getName() + ", getCave()=" + getCave() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
	@Override
	public void methodInBear() {
		System.out.println("Method in BearAutowiringByName called. Bear is: " + toString());
	}
}
