package com.revature.models;

public class Location {
	private int locId;
	private String street;
	private String city;
	private String state;
	private int zip;
	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Location(int locId, String street, String city, String state, int zip) {
		super();
		this.locId = locId;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	public int getlocId() {
		return locId;
	}
	public void setlocId(int locId) {
		this.locId = locId;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + locId;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + zip;
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
		Location other = (Location) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (locId != other.locId)
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		if (zip != other.zip)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Location [locId=" + locId + ", street=" + street + ", city=" + city + ", state=" + state + ", zip="
				+ zip + "]";
	}
	
}
