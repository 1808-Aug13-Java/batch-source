package com.revature.daos;

import java.util.List;

import com.revature.models.Invoice;

public interface InvoiceDao {
	public int createInvoice(Invoice invoice);
	public Invoice getInvoiceById(int id);
	public List<Invoice> getInvoices();
	public void updateInvoice(Invoice invoice);
	public void deleteInvoice(Invoice invoice);
}
