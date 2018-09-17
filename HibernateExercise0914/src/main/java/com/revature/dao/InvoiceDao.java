package com.revature.dao;

import java.util.List;

import com.revature.models.InvoiceAnnot;


public interface InvoiceDao {
	public List<InvoiceAnnot> getInvoices();
	public List<InvoiceAnnot> getInvoiceById(int id);
	public int createInvoice(InvoiceAnnot i);
	public void deleteInvoice(int id);
	public int updateInvoice(InvoiceAnnot i);
	
}
