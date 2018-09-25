package com.revature.services;

import java.util.List;

import com.revature.models.InvoiceHibernate;

public interface InvoiceService {
	public List<InvoiceHibernate> findAllInvoices();
	public InvoiceHibernate findInvoiceById(Long id);
	public InvoiceHibernate saveInvoice(InvoiceHibernate newInvoice);
	public InvoiceHibernate updateInvoice(InvoiceHibernate invoice);
	public void deleteInvoice(InvoiceHibernate invoice);
}
