package com.revature.services;

import java.util.List;

import com.revature.models.Invoice;


public interface InvoiceService {
	public List<Invoice> findAllInvoices();
	public Invoice findInvoiceById(Long id);
	public Invoice addInvoice(Invoice invoice);
	public Invoice updateInvoice(Invoice invoice);
	public Invoice deleteInvoice(Invoice invoice);
}
