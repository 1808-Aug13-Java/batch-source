package com.revature.dao;

import java.util.List;

import com.revature.models.Invoice;

public interface InvoiceDAO {
	
	public List<Invoice> getInvoices();
	public List<Invoice> getInvoicesByCustomer(int customerId);
	public Invoice getInvoiceById(int id);
	public int createInvoice(Invoice inv);
	public void deleteInvoice(Invoice inv);
	
	
}
