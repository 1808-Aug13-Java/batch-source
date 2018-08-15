package com.revature.io;

import java.io.Serializable;

public class Cereal implements Serializable {
	
	private static final long serialVersionUID = 1373285469877185683L;
	
	private boolean hasMarshmallows;
	private transient int calories;
	private transient int sugar;
	private String name;
	
	public Cereal() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Cereal(boolean hasMarshmallows, int calories, int sugar, String name) {
		super();
		this.hasMarshmallows = hasMarshmallows;
		this.calories = calories;
		this.sugar = sugar;
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
	
	public int getSugar() {
		return sugar;
	}
	
	public void setSugar(int sugar) {
		this.sugar = sugar;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Cereal [hasMarshmallows=" + hasMarshmallows + ", calories=" + calories + ", sugar=" + sugar + ", name="
				+ name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + calories;
		result = prime * result + (hasMarshmallows ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + sugar;
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
		if (hasMarshmallows != other.hasMarshmallows)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sugar != other.sugar)
			return false;
		return true;
	}
	
	

}
