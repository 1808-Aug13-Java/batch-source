package com.revature.dao;

import java.util.List;

import com.revature.models.InvoiceHibernate;

public interface InvoiceHibernateDao {
	public List<InvoiceHibernate> getInvoices();
	public InvoiceHibernate getInvoiceById(int id);
	public int createInvoice(InvoiceHibernate c);
	public void updateInvoice(InvoiceHibernate c);
	public void deleteInvoiceById(int id);
}
