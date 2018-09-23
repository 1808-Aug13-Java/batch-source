package com.revature.services;

import java.util.List;

import com.revature.models.Invoices;


public interface InvoiceService {
	
	public List<Invoices> findAllInvoices();
	public Invoices findInvoiceById(Long id);
	public Invoices addInvoice(Invoices newInvoice);
	public Invoices deleteInvoice(Invoices invoice);

}
