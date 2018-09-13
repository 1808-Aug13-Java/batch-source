package com.revature.models;

import java.sql.Date;

public class Invoice 
{
	private int invoiceId;
	private Date date;
	private float amount;
	private int customerId;
	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Invoice(int invoiceId, Date date, float amount, int cust) {
		super();
		this.invoiceId = invoiceId;
		this.date = date;
		this.amount = amount;
		customerId = cust;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	@Override
	public String toString() {
		return "Invoice [InvoiceId=" + invoiceId + ", date=" + date + ", amount=" + amount + ", customerId="
				+ customerId + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + invoiceId;
		result = prime * result + Float.floatToIntBits(amount);
		result = prime * result + customerId;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Invoice))
			return false;
		Invoice other = (Invoice) obj;
		if (invoiceId != other.invoiceId)
			return false;
		if (Float.floatToIntBits(amount) != Float.floatToIntBits(other.amount))
			return false;
		if (customerId != other.customerId)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}
	public int getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}

	

}
