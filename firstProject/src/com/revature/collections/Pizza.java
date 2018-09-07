package com.revature.collections;

import java.util.Arrays;

public class Pizza implements Comparable<Pizza>{

	private int diameter;
	private String[] toppings;
	private int slices;
	private String crustType;
	
	
	public Pizza(String[] toppings, int diameter,  int slices, String crustType) {
		super();
		this.diameter = diameter;
		this.toppings = toppings;
		this.slices = slices;
		this.crustType = crustType;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((crustType == null) ? 0 : crustType.hashCode());
		result = prime * result + diameter;
		result = prime * result + slices;
		result = prime * result + Arrays.hashCode(toppings);
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
		Pizza other = (Pizza) obj;
		if (crustType == null) {
			if (other.crustType != null)
				return false;
		} else if (!crustType.equals(other.crustType))
			return false;
		if (diameter != other.diameter)
			return false;
		if (slices != other.slices)
			return false;
		if (!Arrays.equals(toppings, other.toppings))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "\nPizza [diameter=" + diameter + ", toppings=" + Arrays.toString(toppings) + ", slices=" + slices
				+ ", crustType=" + crustType + "]";
	}
	public int getDiameter() {
		return diameter;
	}
	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}
	public String[] getToppings() {
		return toppings;
	}
	public void setToppings(String[] toppings) {
		this.toppings = toppings;
	}
	public int getSlices() {
		return slices;
	}
	public void setSlices(int slices) {
		this.slices = slices;
	}
	public String getCrustType() {
		return crustType;
	}
	public void setCrustType(String crustType) {
		this.crustType = crustType;
	}
	@Override
	public int compareTo(Pizza arg0) {
		if (this.slices < arg0.slices) {
			return 1;
		}else if(this.slices == arg0.slices) {
			return 0;
		}
		return -1;
	}
	
}
