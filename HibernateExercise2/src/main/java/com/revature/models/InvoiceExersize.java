package com.revature.models;

import java.sql.Date;
import javax.persistence.*;

@Entity
@Table
public class InvoiceExersize {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="invoiceSequence")
	@SequenceGenerator(name="invoiceSequence", allocationSize=1, sequenceName="SQ_INVOICE_PK")
	@Column(name="INVOICE_ID")
	private int id;
	
	@Column(name="INVOICE_DAY")
	private Date day;
	
	@Column(name="INVOICE_AMT")
	private double amount;
	
	public InvoiceExersize() {
		super();
	}
	
	public InvoiceExersize(int id, Date day, double amount) {
		super();
		this.id = id;
		this.day = day;
		this.amount = amount;
	}
	
	public InvoiceExersize(Date day, double amount) {
		super();
		this.id = 0;
		this.day = day;
		this.amount = amount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
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
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + id;
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
		InvoiceExersize other = (InvoiceExersize) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "InvoiceExersize [id=" + id + ", day=" + day + ", amount=" + amount + "]";
	}
}
