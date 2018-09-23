package com.revature.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="INVOICE")
public class Invoices {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="invoiceSequence")
	@SequenceGenerator(name="invoiceSequence", allocationSize=1, sequenceName="SQ_INVOICE_PK")
	@Column(name="INVOICE_ID")
	@NotNull
	private Long invoiceId;
	
	@Column(name="AMT")
	private Long amt;

	@Column(name="INVOICE_DATE")
	@DateTimeFormat
	private Date invoiceDate;
	
	@Id
	@Column(name="CUSTOMER_ID")
	@NotNull
	private Long customerId;

	
	public Invoices() {
		super();
	}

	/**
	 * @param invoiceId
	 * @param amt
	 * @param invoiceDate
	 * @param customerId
	 */
	public Invoices(@NotNull Long invoiceId, Long amt, Date invoiceDate, @NotNull Long customerId) {
		super();
		this.invoiceId = invoiceId;
		this.amt = amt;
		this.invoiceDate = invoiceDate;
		this.customerId = customerId;
	}

	/**
	 * @return the invoiceId
	 */
	public Long getInvoiceId() {
		return invoiceId;
	}

	/**
	 * @param invoiceId the invoiceId to set
	 */
	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	/**
	 * @return the amt
	 */
	public Long getAmt() {
		return amt;
	}

	/**
	 * @param amt the amt to set
	 */
	public void setAmt(Long amt) {
		this.amt = amt;
	}

	/**
	 * @return the invoiceDate
	 */
	public Date getInvoiceDate() {
		return invoiceDate;
	}

	/**
	 * @param invoiceDate the invoiceDate to set
	 */
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	/**
	 * @return the customerId
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amt == null) ? 0 : amt.hashCode());
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((invoiceDate == null) ? 0 : invoiceDate.hashCode());
		result = prime * result + ((invoiceId == null) ? 0 : invoiceId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Invoices other = (Invoices) obj;
		if (amt == null) {
			if (other.amt != null)
				return false;
		} else if (!amt.equals(other.amt))
			return false;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (invoiceDate == null) {
			if (other.invoiceDate != null)
				return false;
		} else if (!invoiceDate.equals(other.invoiceDate))
			return false;
		if (invoiceId == null) {
			if (other.invoiceId != null)
				return false;
		} else if (!invoiceId.equals(other.invoiceId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Invoices [invoiceId=" + invoiceId + ", amt=" + amt + ", invoiceDate=" + invoiceDate + ", customerId="
				+ customerId + "]";
	}

}
