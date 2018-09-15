package com.revature.models;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "INVOICE", schema = "JGONZD")
@NamedQueries({@NamedQuery(name="getInvoiceById", query="from Invoice where id = :idVar")})

public class Invoice {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="invoiceSequence")
	@SequenceGenerator(name="invoiceSequence" , allocationSize=1, sequenceName="SQ_INVOICE_PK")
	@Column(name="INVOICE_ID")
	private int invoiceId;
	
	@Column(name="INVOICE_DATE")
	private Date date;
	
	@Column
	private double amount;
	
	@Column
	private int customerId;
	
	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}

	


	public Invoice(int invoiceId, Date date, double amount, int customerId) {
		super();
		this.invoiceId = invoiceId;
		this.date = date;
		this.amount = amount;
		this.customerId = customerId;
	}



	public int getInvoiceId() {
		return invoiceId;
	}



	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}




	public int getCustomerId() {
		return customerId;
	}




	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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
		result = prime * result + customerId;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + invoiceId;
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
		Invoice other = (Invoice) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (customerId != other.customerId)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (invoiceId != other.invoiceId)
			return false;
		return true;
	}




	@Override
	public String toString() {
		return "Invoice [invoiceId=" + invoiceId + ", date=" + date + ", amount=" + amount + ", customerId="
				+ customerId + "]";
	}

	
		
}
