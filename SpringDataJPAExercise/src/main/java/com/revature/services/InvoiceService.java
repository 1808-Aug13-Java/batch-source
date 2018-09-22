package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Invoice;
import com.revature.repositories.InvoiceRepository;

@Service
public class InvoiceService implements InvoiceServiceInterface{

	@Autowired
	InvoiceRepository invoiceRepo;
	
	@Override
	public List<Invoice> findAllInvoices() {
		return invoiceRepo.findAll(); 
	}

	@Override
	public Invoice findInvoiceById(Integer id) {
		return invoiceRepo.getOne(id);
	}

	@Override
	public Invoice addInvoice(Invoice c) {
		return invoiceRepo.save(c);
	}

	@Override
	public Invoice updateInvoice(Invoice c) {
		return invoiceRepo.save(c);
	}

	@Override
	public Invoice deleteInvoice(Invoice c) {
		invoiceRepo.delete(c);
		return c;
	}

	@Override
	public Invoice deleteInvoiceById(Integer id) {
		Invoice c = invoiceRepo.getOne(id);
		invoiceRepo.delete(c);
		return c;
	}

}
