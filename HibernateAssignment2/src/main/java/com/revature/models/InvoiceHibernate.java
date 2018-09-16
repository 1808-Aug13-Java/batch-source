package com.revature.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="INVOICE_HIBERNATE")
public class InvoiceHibernate {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sequenceInvIds")
	@SequenceGenerator(name="sequenceInvIds", allocationSize=1, sequenceName="SQ_INVOICE_ID_GENERATE")
	@Column(name="INV_ID")
	private int invId;
	@Column(name="AMOUNT")
	private float amount;
	@Column(name="CUS_ID")
	private int cusId; 
	@Column(name="DATE")
	private Date date;
	
	
	public InvoiceHibernate() {
		super();
	}
	public InvoiceHibernate(float amount, int cusId, Date date) {
		super();
//		this.invId = invId;
		this.amount = amount;
		this.cusId = cusId;
		this.date = date;
	}
	public InvoiceHibernate(int invId, float amount, int cusId, Date date) {
		super();
		this.invId = invId;
		this.amount = amount;
		this.cusId = cusId;
		this.date = date;
	}
	public int getInvId() {
		return invId;
	}
	public float getAmount() {
		return amount;
	}
	public int getCusId() {
		return cusId;
	}
	public Date getDate() {
		return date;
	}
	public void setInvId(int invId) {
		this.invId = invId;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Invoice [invId=" + invId + ", amount=" + amount + ", cusId=" + cusId + ", date=" + date + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(amount);
		result = prime * result + cusId;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + invId;
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
		InvoiceHibernate other = (InvoiceHibernate) obj;
		if (Float.floatToIntBits(amount) != Float.floatToIntBits(other.amount))
			return false;
		if (cusId != other.cusId)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (invId != other.invId)
			return false;
		return true;
	} 
	
	
}
