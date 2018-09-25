package com.revature.models;

public class Bear {
	
	private String name;
	private String caveName;
	private int birthYear;
	/**
	 * 
	 */
	public Bear() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param name
	 * @param caveName
	 * @param birthYear
	 */
	public Bear(String name, String caveName, int birthYear) {
		super();
		this.name = name;
		this.caveName = caveName;
		this.birthYear = birthYear;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCaveName() {
		return caveName;
	}
	public void setCaveName(String caveName) {
		this.caveName = caveName;
	}
	public int getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + birthYear;
		result = prime * result + ((caveName == null) ? 0 : caveName.hashCode());
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
		Bear other = (Bear) obj;
		if (birthYear != other.birthYear)
			return false;
		if (caveName == null) {
			if (other.caveName != null)
				return false;
		} else if (!caveName.equals(other.caveName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Bear [name=" + name + ", caveName=" + caveName + ", birthYear=" + birthYear + "]";
	}
	
	
	
}