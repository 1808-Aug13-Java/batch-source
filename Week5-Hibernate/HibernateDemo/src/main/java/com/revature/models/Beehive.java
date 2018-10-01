package com.revature.models;

import javax.persistence.*;

@Entity
@Table
public class Beehive {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="beehiveSequence")
	@SequenceGenerator(name="beehiveSequence", allocationSize=1, sequenceName="SQ_BEEHIVE_PK")
	@Column(name="BEEHIVE_ID")
	private int id;
	
	@Column
	private int weight;
	
	public Beehive() {
		super();
	}

	public Beehive(int weight) {
		super();
		this.weight = weight;
	}
	
	public Beehive(int id, int weight) {
		super();
		this.id = id;
		this.weight = weight;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + weight;
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
		Beehive other = (Beehive) obj;
		if (id != other.id)
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Beehive [id=" + id + ", weight=" + weight + "]";
	}
	
}
