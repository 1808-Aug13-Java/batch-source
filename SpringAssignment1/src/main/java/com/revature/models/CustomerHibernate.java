package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table
public class CustomerHibernate {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cusIdSequence")
	@SequenceGenerator(name="cusIdSequence", allocationSize=1, sequenceName="SQ_CUS_PK")
	@Column (name="CUS_ID")
	private Long cusId;
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
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
	
	public CustomerHibernate(Long cusId, String name, String email, String phone) {
		super();
		this.cusId = cusId;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
	public Long getcusId() {
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
	public void setcusId(Long cusId) {
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
	}
	
	
}
