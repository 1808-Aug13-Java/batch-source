package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Entity
@Table
@Component
public class Beverage {
	
	@Id
	@Column(name="BEVERAGE_ID")
	@NotNull
	private Integer id;
	
	@Column
	private String brand;
	
	@Column(name="BEVERAGE_SIZE")
	private float size;
	
	@Column
	private boolean hasIce;
	
	@Column
	private String flavor;

	public Beverage() {
		super();
		this.id = 0;
	}

	public Beverage(@NotNull Integer id, String brand, float size, boolean hasIce, String flavor) {
		super();
		this.id = id;
		this.brand = brand;
		this.size = size;
		this.hasIce = hasIce;
		this.flavor = flavor;
	}

	public Beverage(String brand, float size, boolean hasIce, String flavor) {
		super();
		this.id = 0;
		this.brand = brand;
		this.size = size;
		this.hasIce = hasIce;
		this.flavor = flavor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public boolean isHasIce() {
		return hasIce;
	}

	public void setHasIce(boolean hasIce) {
		this.hasIce = hasIce;
	}

	public String getFlavor() {
		return flavor;
	}

	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((flavor == null) ? 0 : flavor.hashCode());
		result = prime * result + (hasIce ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + Float.floatToIntBits(size);
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
		Beverage other = (Beverage) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (flavor == null) {
			if (other.flavor != null)
				return false;
		} else if (!flavor.equals(other.flavor))
			return false;
		if (hasIce != other.hasIce)
			return false;
		if (id != other.id)
			return false;
		if (Float.floatToIntBits(size) != Float.floatToIntBits(other.size))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Beverages [id=" + id + ", brand=" + brand + ", size=" + size + ", hasIce=" + hasIce + ", flavor="
				+ flavor + "]";
	}
	
	
}
