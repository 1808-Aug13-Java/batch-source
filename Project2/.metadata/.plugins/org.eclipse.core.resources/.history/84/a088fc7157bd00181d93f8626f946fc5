package com.revature.salutem.models;

import javax.persistence.*;

//@Entity
//@Table
public class Specialization {
//	
//	@Id
//	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="specializationSequence")
//	@SequenceGenerator(name="specializationSequence",allocationSize=1,sequenceName="SQ_SPEC_PK")
//	@Column(name="SPECIALIZION_ID")
	private int id;
//	@Column
	private String name;
//	@Column(name="SPECIALIST_ID")
	private int specialistId;
	
	public Specialization() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Specialization(int id, String name, int specialistId) {
		super();
		this.id = id;
		this.name = name;
		this.specialistId = specialistId;
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
	public int getSpecialistId() {
		return specialistId;
	}
	public void setSpecialistId(int specialistId) {
		this.specialistId = specialistId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + specialistId;
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
		Specialization other = (Specialization) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (specialistId != other.specialistId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Specialization [id=" + id + ", name=" + name + ", specialistId=" + specialistId + "]";
	}
	
	
}
