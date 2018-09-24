package com.revature.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="SPIRNG_INVOICE")
public class Invoice {
	@Id
	@Column
	@NotNull
	private Long invId;
	
	@Column
	private Date invDate;
	
	@Column
	private int amount;
	
	@ManyToOne
	@JoinColumn(name="CUST_ID")
	private Customer customer;

	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Invoice(@NotNull Long invId, Date invDate, int amount, Customer customer) {
		super();
		this.invId = invId;
		this.invDate = invDate;
		this.amount = amount;
		this.customer = customer;
	}

	public Long getInvId() {
		return invId;
	}

	public void setInvId(Long invId) {
		this.invId = invId;
	}

	public Date getInvDate() {
		return invDate;
	}

	public void setInvDate(Date invDate) {
		this.invDate = invDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((invDate == null) ? 0 : invDate.hashCode());
		result = prime * result + ((invId == null) ? 0 : invId.hashCode());
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
		if (amount != other.amount)
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (invDate == null) {
			if (other.invDate != null)
				return false;
		} else if (!invDate.equals(other.invDate))
			return false;
		if (invId == null) {
			if (other.invId != null)
				return false;
		} else if (!invId.equals(other.invId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Invoice [invId=" + invId + ", invDate=" + invDate + ", amount=" + amount + ", customer=" + customer
				+ "]";
	}
	
	
}
