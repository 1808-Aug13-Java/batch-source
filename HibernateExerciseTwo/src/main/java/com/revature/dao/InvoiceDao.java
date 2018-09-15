package com.revature.dao;

import java.util.List;

import com.revature.models.Invoice;

public interface InvoiceDao {
	
	public Invoice getInvoiceById(int id);
	public List<Invoice> getInvoice();
	
	public int createInvoice(Invoice invoice);
	public void updateInvoice(Invoice invoice);
	public void deleteInvoice(int id);

}
