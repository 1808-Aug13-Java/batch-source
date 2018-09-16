package com.revature.dao;

import java.util.List;

import com.revature.models.Invoice;

public interface InvoiceDao {
	
	List<Invoice> getInvoices();
	public Invoice getInvoiceById(int id);
	public int createInvoice(Invoice i);
	public void updateInvoice(Invoice i);
	public void deleteInvoiceById(int id);
	public long getInvoiceCount();
}
