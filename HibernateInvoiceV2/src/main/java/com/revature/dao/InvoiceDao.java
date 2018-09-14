package com.revature.dao;

import java.util.List;

import com.revature.models.Invoice;

public interface InvoiceDao 
{
	public List<Invoice> getInvoice();
	public Invoice getInvoiceById(int id);
	public int createInvoice(Invoice b);
	public void updateInvoice(Invoice c);
	public void deleteInvoice(int id);

}
