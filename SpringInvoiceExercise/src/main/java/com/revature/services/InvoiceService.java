package com.revature.services;

import java.util.List;

import com.revature.models.Invoice;

public interface InvoiceService
{
	public List<Invoice> findAllInvoices();
	public Invoice findInvoiceById(Integer id);
	public Invoice addInvoice(Invoice newInvoice);
	public Invoice updateInvoice(Invoice u);
	public Invoice deleteInvoice(Invoice u);
}
