package com.revature.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.models.Invoice;

@Service
public interface InvoiceService {
	public List<Invoice> getAllInvoices();
	public Invoice getInvoiceById(int id);
	public Invoice addInvoice(Invoice inv);
	public void updateInvoice(Invoice inv);
	public void deleteInvoiceById(int id);
}
