package com.revature.models;

import javax.persistence.*;

@Entity
@Table
public class Cave {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="caveSequence")
	@SequenceGenerator(name="caveSequence", allocationSize=1, sequenceName="SQ_CAVE_PK")
	@Column(name="CAVE_ID")
	private int id;
	@Column(name="CAVE_NAME")
	private String name;
	public Cave() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cave(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Cave(String name) {
		super();
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Cave other = (Cave) obj;
		if (id != other.id)
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
		return "Cave [id=" + id + ", name=" + name + "]";
	}
	
	
}
