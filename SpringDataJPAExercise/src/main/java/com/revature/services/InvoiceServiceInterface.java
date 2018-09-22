package com.revature.services;

import java.util.List;

import com.revature.models.Invoice;

public interface InvoiceServiceInterface {

	public List<Invoice> findAllInvoices();
	public Invoice findInvoiceById(Integer id);
	public Invoice addInvoice(Invoice c);
	public Invoice updateInvoice(Invoice c);
	public Invoice deleteInvoice(Invoice c);
	public Invoice deleteInvoiceById(Integer id);
}
