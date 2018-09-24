package com.revature.services;

import java.util.List;

import com.revature.models.Invoice;

public interface InvoiceService {

	public List<Invoice> findAllInvoices();
	public Invoice getInvoiceById(int id);
	public Invoice updateInvoice(Invoice invoice);
	public Invoice deleteInvoice(Invoice invoice);
	public Invoice addInvoice(Invoice invoice);
}
