package com.revature.dao;

import java.util.List;

import com.revature.models.Invoice;

public interface InvoiceDao {
	public Invoice getInvoiceById(int invId);
	public List<Invoice> getInvoices();
	
	public int createInvoice(Invoice inv);
	public void updateInvoice(Invoice inv);
	public void deleteInvoiceById(int invId);
	
}
