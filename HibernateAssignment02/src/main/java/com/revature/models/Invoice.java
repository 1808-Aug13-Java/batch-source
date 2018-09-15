package com.revature.models;

import java.sql.Date;

import javax.persistence.*;


@Entity
@Table
public class Invoice {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="invoiceSequence")
	@SequenceGenerator(name="invoiceSequence", allocationSize=1, sequenceName="SQ_INVOICE_PK")
	@Column(name="INVOICE_ID")
	int id;
	
	@Column(name="SUBMIT_DATE")
	Date submitDate;
	
	@Column
	double amount;
	
	
	public Invoice(int id, Date submitDate, double amount) {
		super();
		this.id = id;
		this.submitDate = submitDate;
		this.amount = amount;
	}
	public Invoice() {
		super();
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "Invoice [id=" + id 
				+ ", submitDate=" + submitDate 
				+ ", amount=" + amount 
				+ "]";
	}
	
	
}
