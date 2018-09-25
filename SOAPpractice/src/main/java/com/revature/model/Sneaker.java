package com.revature.model;

public class Sneaker {

	private String name;
	private String brand;
	private float price;
	private String gender;
	
	
	public Sneaker() {
		super();
	}


	public Sneaker(String name, String brand, float price, String gender) {
		super();
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.gender = gender;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Float.floatToIntBits(price);
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Sneaker)) {
			return false;
		}
		Sneaker other = (Sneaker) obj;
		if (brand == null) {
			if (other.brand != null) {
				return false;
			}
		} else if (!brand.equals(other.brand)) {
			return false;
		}
		if (gender == null) {
			if (other.gender != null) {
				return false;
			}
		} else if (!gender.equals(other.gender)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price)) {
			return false;
		}
		return true;
	}


	@Override
	public String toString() {
		return "Sneaker [" + (name != null ? "name=" + name + ", " : "")
				+ (brand != null ? "brand=" + brand + ", " : "") + "price=" + price + ", "
				+ (gender != null ? "gender=" + gender : "") + "]";
	}
	
	
}
