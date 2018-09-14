package com.revature.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.revature.dao.CustomerDao;
import com.revature.dao.CustomerDaoImp;


@Entity
@Table
public class Invoice 
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="invoiceSequence")
	@SequenceGenerator(name="invoiceSequence", allocationSize=1, sequenceName="SQ_INVOICE_PK")
	@Column(name="INVOICE_ID")
	private int invoiceId;
	
	@Column(name="INVOICE_DATE")
	private Date date;
	
	@Column(name="INVOICE_AMOUNT")
	private float amount;
	
	
	@ManyToOne
	@JoinColumn(name="CUST_ID")
	private Customer customerId;
	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Invoice(int invoiceId, Date date, float amount, int cust) {
		super();
		this.invoiceId = invoiceId;
		this.date = date;
		this.amount = amount;
		CustomerDao cd = new CustomerDaoImp();
		
		customerId = cd.getCustomerById(cust);
	}
	
	public Invoice(int invoiceId, Date date, float amount, Customer cust) {
		super();
		this.invoiceId = invoiceId;
		this.date = date;
		this.amount = amount;
		
		customerId = cust;
	}
	
	public Invoice(Date date, float amount, Customer cust)
	{
		super();
		this.date = date;
		this.amount = amount;
		
		customerId = cust;
	}
	
	public Customer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Customer customerId) {
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
