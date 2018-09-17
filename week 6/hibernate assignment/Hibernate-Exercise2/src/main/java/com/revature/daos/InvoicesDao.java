package com.revature.daos;

import java.util.Date;
import java.util.List;

import com.revature.models.Invoices;

public interface InvoicesDao {

	public List<Invoices> getInvoices();
	public Invoices getInvoicesById(int id);
	public int createInvoices(Invoices c);
	public long getInvoicesCount();
	public void deleteInvoicesById(int id);
	public void updateInvoices(Invoices c);
	List<Invoices> getInvoicesByDate(Date date);
	
	
}
