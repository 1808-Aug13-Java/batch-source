package com.revature.daos;

import java.util.Date;
import java.util.List;

import com.revature.models.Invoice;

public interface InvoiceDao {

		public int createInvoice(Invoice i);
		public int deleteInvoice(int invoiceId);
		public int updateInvoice(int id,Date date, double amount);
		public Invoice getInvoiceById(int inoviceId);
		public List<Invoice> getAllInvoices();
		
}
