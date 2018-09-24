package com.revature.services;

import java.util.List;

import com.revature.models.Invoice;

public interface InvoiceService {
	public List<Invoice> findAllInvoices();
	public Invoice findInvoiceById(int id);
	public Invoice addInvoice(Invoice newInvoice);
	public Invoice updateInvoice(Invoice invoice);
	public void deleteInvoice(Invoice invoice);
}
