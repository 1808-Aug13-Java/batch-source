package com.revature.io;

import java.io.Serializable;

public class Cereal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6246998866482770557L;
	private boolean hasMarshmallows;
	private transient int calories;
	private transient int gramsOfSugar;
	private String name;
	
	
	public Cereal(boolean hasMarshmallows, int calories, int gramsOfSugar, String name) {
		super();
		this.hasMarshmallows = hasMarshmallows;
		this.calories = calories;
		this.gramsOfSugar = gramsOfSugar;
		this.name = name;
	}
	
	public boolean isHasMarshmallows() {
		return hasMarshmallows;
	}
	public void setHasMarshmallows(boolean hasMarshmallows) {
		this.hasMarshmallows = hasMarshmallows;
	}
	public int getCalories() {
		return calories;
	}
	public void setCalories(int calories) {
		this.calories = calories;
	}
	public int getGramsOfSugar() {
		return gramsOfSugar;
	}
	public void setGramsOfSugar(int gramsOfSugar) {
		this.gramsOfSugar = gramsOfSugar;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Cereal [hasMarshmallows=" + hasMarshmallows + ", calories=" + calories + ", gramsOfSugar="
				+ gramsOfSugar + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + calories;
		result = prime * result + gramsOfSugar;
		result = prime * result + (hasMarshmallows ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cereal other = (Cereal) obj;
		if (calories != other.calories)
			return false;
		if (gramsOfSugar != other.gramsOfSugar)
			return false;
		if (hasMarshmallows != other.hasMarshmallows)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
	
	
}
