package com.revature.daos;

import java.util.List;

import com.revature.models.Invoice;

public interface InvoiceDao {
	public List<Invoice> getInvoices();
	public Invoice getInvoiceById(int id);
	public int createInvoice(Invoice i);
	public void updateInvoice(Invoice i);
	public void deleteInvoice(int id);
	public List<Invoice> getInvoicesByCustomerId(int id);
}
