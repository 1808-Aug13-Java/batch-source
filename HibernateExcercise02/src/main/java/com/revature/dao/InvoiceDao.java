package com.revature.dao;

import java.util.List;

import com.revature.models.Invoice;

public interface InvoiceDao {
	public List<Invoice> getInvoices();
	public Invoice getInvoiceById(int id);
	public int createInvoice(Invoice inv);
	public int updateInvoice(int id);
	public int deleteInvoice(int id);
}
