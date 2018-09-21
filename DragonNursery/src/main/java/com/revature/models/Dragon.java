package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table
@Component
public class Dragon {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="dragonSequence")
	@SequenceGenerator(name="dragonSequence", allocationSize =1, sequenceName = "SQ_DRAGON_PK")
	@Column(name = "DRAGON_ID")
	private Long id;
	
	@Column(name = "DRAGON_NAME")
	private String name;
	
	@Column(name = "DRAGON_ELEMENT")
	private String element;
	
	@Column(name = "DRAGON_BREED")
	private String breed; // different classes of dragons
	
	@Column(name = "DRAGON_MONEY")
	private int money;

	public Dragon() {
		super();
	}
	
	public Dragon(Long id, String name, String element, String breed, int money) {
		super();
		this.id = id;
		this.name = name;
		this.element = element;
		this.breed = breed;
		this.money = money;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((breed == null) ? 0 : breed.hashCode());
		result = prime * result + ((element == null) ? 0 : element.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + money;
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
		Dragon other = (Dragon) obj;
		if (breed == null) {
			if (other.breed != null)
				return false;
		} else if (!breed.equals(other.breed))
			return false;
		if (element == null) {
			if (other.element != null)
				return false;
		} else if (!element.equals(other.element))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (money != other.money)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Dragon [id=" + id + ", name=" + name + ", element=" + element + ", breed=" + breed
				+ ", money=" + money + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
	

}