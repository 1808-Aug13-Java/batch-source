package com.chandrika.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chandrika.models.Invoice;
import com.chandrika.repositories.InvoiceRepository;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	InvoiceRepository invoiceRepo;
	public List<Invoice> getAllInvoices() {
		return invoiceRepo.findAll();
	}

	@Override
	public Invoice getInvoiceById(long id) {
		return invoiceRepo.getOne(id);
	}

	@Override
	public Invoice addInvoice(Invoice newInvoice) {
		return invoiceRepo.save(newInvoice);
	}

	@Override
	public Invoice updateInvoice(Invoice invoice) {
		return invoiceRepo.save(invoice);
	}

	@Override
	public Invoice deleteCustomer(Invoice invoice) {
		invoiceRepo.delete(invoice);
		return invoice;
	}
	
}
