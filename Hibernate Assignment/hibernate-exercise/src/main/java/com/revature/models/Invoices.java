package com.revature.models;

import java.util.Date;

public class Invoices {

	private int InvoiceId;
	private Date date;
	private double amount;
	
	public Invoices() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Invoices(int invoiceId, Date date, double amount) {
		super();
		InvoiceId = invoiceId;
		this.date = date;
		this.amount = amount;
	}

	public int getInvoiceId() {
		return InvoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		InvoiceId = invoiceId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double isAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + InvoiceId;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
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
		if (InvoiceId != other.InvoiceId)
			return false;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Invoice [InvoiceId=" + InvoiceId + ", date=" + date + ", amount=" + amount + "]";
	}
	
	
	
	
	
	
}
