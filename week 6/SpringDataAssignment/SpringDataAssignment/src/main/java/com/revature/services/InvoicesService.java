package com.revature.services;

import java.util.List;

import com.revature.models.Invoices;

public interface InvoicesService {
	
	public List<Invoices> findAllInvoices();
	public Invoices findInvoicesById(Long id);
	public Invoices addInvoices(Invoices newInvoice);
	public Invoices updateInvoices(Invoices invoice);
	public void deleteInvoices(Long id);

}
