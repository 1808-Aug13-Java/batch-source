package com.revature.dao;

import java.util.List;

import com.revature.models.Invoice;

public interface InvoiceDao {
	public List<Invoice> getInvoices();
	public Invoice getInvoiceById(int id);
	public int createInvoice(Invoice i);
	public List<Invoice> getInvoicesByCustomer(int customerId);
	public List<Invoice> getInvoiceByName(String name);
	public long getInvoiceCount();
}
