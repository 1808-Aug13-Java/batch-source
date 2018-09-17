package com.revature.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table
public class Invoices {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INVOICESEQUENCE")
	@SequenceGenerator(name="INVOICESEQUENCE", allocationSize=1, sequenceName="SQ_INV_PK")
	@Column(name="INV_ID")
	private int invid;
	@Column(name="DATES")
	private Date date;
	@Column(name="AMOUNT")
	private double amount;
	
	
	public Invoices() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Invoices(int invid, Date date, double amount) {
		super();
		this.invid = invid;
		this.date = date;
		this.amount = amount;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + invid;
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
		Invoices other = (Invoices) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (invid != other.invid)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Invoices [invid=" + invid + ", date=" + date + ", amount=" + amount + "]";
	}


	public int getInvid() {
		return invid;
	}


	public void setInvid(int invid) {
		this.invid = invid;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	
	
	
	
	
	
	
	
	
}
