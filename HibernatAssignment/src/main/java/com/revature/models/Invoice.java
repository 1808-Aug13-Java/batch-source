package com.revature.models;

import java.sql.Date;

public class Invoice {
	int id;
	Date submitDate;
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
