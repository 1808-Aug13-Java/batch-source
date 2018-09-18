package com.revature.dao;
import java.util.List;
import com.revature.models.Invoice;
public interface InvoiceDAO {
	public List<Invoice> getInvoices();
	public Invoice getInvoicesById(int id);
	public int createInvoice(Invoice i);
	public long getInvoiceCount();
	public void deleteInvoice(int id);
	public void updateInvoice(Invoice i);
}
