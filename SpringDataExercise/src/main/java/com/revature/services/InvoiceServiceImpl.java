package com.revature.services;

import java.util.List;

import com.revature.models.Invoice;
import com.revature.repositories.InvoiceRepository;

public class InvoiceServiceImpl implements InvoiceService {
	
	InvoiceRepository invoiceRepo;

	@Override
	public Invoice createInvoice(Invoice invoice) {
		invoiceRepo.save(invoice);
		return invoice;
	}

	@Override
	public Invoice getInvoiceById(int id) {
		Invoice i = invoiceRepo.getOne(id);
		return i;
	}

	@Override
	public List<Invoice> getInvoices() {
		List<Invoice> invoices = invoiceRepo.findAll();
		return invoices;
	}

	@Override
	public Invoice updateInvoice(Invoice invoice) {
		invoiceRepo.save(invoice);
		return invoice;
	}

	@Override
	public Invoice deleteInvoice(Invoice invoice) {
		invoiceRepo.delete(invoice);
		return invoice;
	}

}
