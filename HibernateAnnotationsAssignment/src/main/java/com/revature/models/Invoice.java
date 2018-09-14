package com.revature.models;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.*;

@Entity
@Table
public class Invoice {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="invoiceSequence")
	@SequenceGenerator(name="invoiceSequence", allocationSize=1, sequenceName="SQ_INV_PK")
	@Column(name="INVOICE_ID")
	private int invoiceId;
	
	@Column(name="INVOICE_DATE")
	private Date invoiceDate;
	
	@OneToOne
	@JoinColumn(name="CUSTOMER_ID")
	private Customer customer;
	
	@Column
	private BigDecimal amount;
	
	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Invoice(int invoiceId, Date invoiceDate, Customer customer, BigDecimal amount) {
		super();
		this.invoiceId = invoiceId;
		this.invoiceDate = invoiceDate;
		this.customer = customer;
		this.amount = amount;
	}
	public Invoice(Date invoiceDate, Customer customer, BigDecimal amount) {
		super();
		this.invoiceDate = invoiceDate;
		this.customer = customer;
		this.amount = amount;
	}
	public int getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((invoiceDate == null) ? 0 : invoiceDate.hashCode());
		result = prime * result + invoiceId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Invoice other = (Invoice) obj;
		if (amount == null) {
			if (other.amount != null) {
				return false;
			}
		} else if (!amount.equals(other.amount)) {
			return false;
		}
		if (customer == null) {
			if (other.customer != null) {
				return false;
			}
		} else if (!customer.equals(other.customer)) {
			return false;
		}
		if (invoiceDate == null) {
			if (other.invoiceDate != null) {
				return false;
			}
		} else if (!invoiceDate.equals(other.invoiceDate)) {
			return false;
		}
		if (invoiceId != other.invoiceId) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		return "Invoice [invoiceId=" + invoiceId + ", invoiceDate=" + invoiceDate + ", customer=" + customer
				+ ", amount=" + amount + "]";
	}
	

}