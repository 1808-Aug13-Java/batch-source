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
	public Invoice getInvoiceById(int id) {
		return invoiceRepo.getOne(id);
	}

	@Override
	public Invoice updateInvoice(Invoice invoice) {
		return invoiceRepo.save(invoice);
	}


	@Override
	public Invoice addInvoice(Invoice invoice) {
		return invoiceRepo.save(invoice);
	}
	
	@Override
	public Invoice deleteInvoice(Invoice invoice) {
		invoiceRepo.delete(invoice);
		return invoice;
	}

}
