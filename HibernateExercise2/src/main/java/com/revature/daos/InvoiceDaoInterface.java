package com.revature.daos;

import java.util.List;

import com.revature.models.InvoiceExersize;

public interface InvoiceDaoInterface {

	public List<InvoiceExersize> getAllInvoices();
	public InvoiceExersize getInvoiceById(int id);
	public int newInvoice(InvoiceExersize inv);
	public void updateInvoice(InvoiceExersize inv);
	public void deleteInvoice(int id);
}
