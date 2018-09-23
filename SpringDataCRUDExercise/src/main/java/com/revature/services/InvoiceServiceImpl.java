package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Invoices;
import com.revature.repositories.InvoiceRepository;

@Service
public class InvoiceServiceImpl implements InvoiceService {
	
	@Autowired
	InvoiceRepository invoiceRepo;

	@Override
	public List<Invoices> findAllInvoices() {
		return invoiceRepo.findAll();
	}

	@Override
	public Invoices findInvoiceById(Long id) {
		return invoiceRepo.getOne(id);
	}

	@Override
	public Invoices addInvoice(Invoices newInvoice) {
		return invoiceRepo.save(newInvoice);
	}

	@Override
	public Invoices deleteInvoice(Invoices invoice) {
		invoiceRepo.delete(invoice);
		return invoice;
	}

}
