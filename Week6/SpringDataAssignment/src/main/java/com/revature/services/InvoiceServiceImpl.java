package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Invoice;
import com.revature.repositories.InvoiceRepository;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	InvoiceRepository invoiceRepo;
	
	@Override
	public List<Invoice> findAllInvoices() {
		return invoiceRepo.findAll();
	}

	@Override
	public Invoice findInvoiceById(int id) {
		return invoiceRepo.findInvoiceById(id);
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
	public void deleteInvoice(Invoice invoice) {
		invoiceRepo.delete(invoice);
	}

}
