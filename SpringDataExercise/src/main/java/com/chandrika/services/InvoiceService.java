package com.chandrika.services;

import java.util.List;
import com.chandrika.models.Invoice;

public interface InvoiceService {
	public List<Invoice> getAllInvoices();
	public Invoice getInvoiceById(long id);
	public Invoice addInvoice(Invoice newInvoice);
	public Invoice updateInvoice(Invoice invoice);
	public Invoice deleteCustomer(Invoice invoice);
}
