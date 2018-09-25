package com.revature.models;

import java.sql.Date;

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
@Table//(name="INVOICE_HIBERNATE")
public class InvoiceHibernate {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="invIdSequence")
	@SequenceGenerator(name="invIdSequence", allocationSize=1, sequenceName="SQ_INV_PK")
	@Column(name="INV_ID")
	private Long invId;
	@Column(name="AMOUNT")
	private float amount;
//	@ManyToOne     //many invoices to one cus_id
//	@JoinColumn(name="CUS_ID")
	@Column(name="CUS_ID")
	private Long cusId; 
	@Column(name="DATE")
	private Date date;
	
	
	public InvoiceHibernate() {
		super();
	}
	public InvoiceHibernate(float amount, Long cusId, Date date) {
		super();
		this.amount = amount;
		this.cusId = cusId;
		this.date = date;
	}
	public InvoiceHibernate(Long invId, float amount, Long cusId, Date date) {
		super();
		this.invId = invId;
		this.amount = amount;
		this.cusId = cusId;
		this.date = date;
	}
	public Long getInvId() {
		return invId;
	}
	public float getAmount() {
		return amount;
	}
	public Long getCusId() {
		return cusId;
	}
	public Date getDate() {
		return date;
	}
	public void setInvId(Long invId) {
		this.invId = invId;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public void setCusId(Long cusId) {
		this.cusId = cusId;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Invoice [invId=" + invId + ", amount=" + amount + ", cusId=" + cusId + ", date=" + date + "]";
	}
	
	
	
}
