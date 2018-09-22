package com.revature.models;

import java.util.Date;

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
@Table(name="INVOICES")
public class Invoices {

	@Id
	@Column
	private Long invid;
	
	@Column
	private Date date;
	
	@Column
	private double amount;

	public Invoices() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Invoices(Long invid, Date date, double amount) {
		super();
		this.invid = invid;
		this.date = date;
		this.amount = amount;
	}

	public Long getInvid() {
		return invid;
	}

	public void setInvid(Long invid) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((invid == null) ? 0 : invid.hashCode());
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
		if (invid == null) {
			if (other.invid != null)
				return false;
		} else if (!invid.equals(other.invid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Invoices [invid=" + invid + ", date=" + date + ", amount=" + amount + "]";
	}

	
	
	
	
	
	
	
	
	
}
