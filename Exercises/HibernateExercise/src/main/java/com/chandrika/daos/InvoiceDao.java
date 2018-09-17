package com.chandrika.daos;

import java.util.List;

import com.chandrika.models.Invoice;

public interface InvoiceDao {
	public List<Invoice> getInvoices();
	public Invoice getInvoiceById(int id);
	public int createInvoice(Invoice b);
	public List<Invoice> getInvoicesByCustomer(int CustomerId);
}
