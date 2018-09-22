package com.revature.services;

import java.util.List;

import com.revature.models.Invoices;

public interface InvoiceService {
	public Invoices createInvoice(Invoices invoice);
	public Invoices getInvoiceById(int id);
	public List<Invoices> getInvoices();
	public Invoices updateInvoice(Invoices invoice);
	public Invoices deleteInvoice(Invoices invoice);
}
