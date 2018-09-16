package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class CustomerHibernate {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cusIdSequence")
	@SequenceGenerator(name="cusIdSequence", allocationSize=1, sequenceName="SQ_CUS_PK")
	@Column(name="CUS_ID")
	private int cusId;
	@Column(name="NAME")
	private String name;
	@Column(name="EMAIL")
	private String email;
	@Column(name="PHONE")
	private String phone;
	
	
	public CustomerHibernate() {
		super();
	}
	
	public CustomerHibernate( String name, String email, String phone) {
		super();
//		this.cusId = cusId;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
	
	public CustomerHibernate(int cusId, String name, String email, String phone) {
		super();
		this.cusId = cusId;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
	public int getcusId() {
		return cusId;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPhone() {
		return phone;
	}
	public void setcusId(int cusId) {
		this.cusId = cusId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Customer [cusId=" + cusId + ", name=" + name + ", email=" + email + ", phone=" + phone + "]";
//		return "Customer [name=" + name + ", email=" + email + ", phone=" + phone + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cusId;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
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
		CustomerHibernate other = (CustomerHibernate) obj;
		if (cusId != other.cusId)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}
	
	
}
