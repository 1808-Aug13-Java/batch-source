package com.revature.services;

import java.util.List;

import com.revature.models.Invoice;

public interface InvoiceService {

	public Invoice createInvoice(Invoice invoice);
	public Invoice getInvoiceById(int id);
	public List<Invoice> getInvoices();
	public Invoice updateInvoice(Invoice invoice);
	public Invoice deleteInvoice(Invoice invoice);
	
}
