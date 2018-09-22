package com.revature.services;

import java.util.List;

import com.revature.models.Invoices;
import com.revature.repositories.InvoiceRepository;

public class InvoiceServiceImpl implements InvoiceService {

	InvoiceRepository invoiceRepository;
	
	@Override
	public Invoices createInvoice(Invoices invoice) {
		invoiceRepository.save(invoice);
		return invoice;
	}

	@Override
	public Invoices getInvoiceById(int id) {
		Invoices invoice = invoiceRepository.getOne(id);
		return invoice;
	}

	@Override
	public List<Invoices> getInvoices() {
		List<Invoices> invoices = invoiceRepository.findAll();
		return invoices;
	}

	@Override
	public Invoices updateInvoice(Invoices invoice) {
		invoiceRepository.save(invoice);
		return invoice;
	}

	@Override
	public Invoices deleteInvoice(Invoices invoice) {
		invoiceRepository.delete(invoice);
		return invoice;
	}
	
	
}
