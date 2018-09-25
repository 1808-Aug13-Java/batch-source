package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.InvoiceHibernate;
import com.revature.repositories.InvoiceRepository;

@Service
public class InvoiceServiceImpl implements InvoiceService{
	@Autowired
	InvoiceRepository invoiceRepo;

	@Override
	public List<InvoiceHibernate> findAllInvoices() {
		return invoiceRepo.findAll();
	}

	@Override
	public InvoiceHibernate findInvoiceById(Long id) {
		return invoiceRepo.getOne(id);
	}

	@Override
	public InvoiceHibernate saveInvoice(InvoiceHibernate newInvoice) {
		return invoiceRepo.save(newInvoice);
	}

	@Override
	public InvoiceHibernate updateInvoice(InvoiceHibernate invoice) {
		return invoiceRepo.save(invoice);
	}

	@Override
	public void deleteInvoice(InvoiceHibernate invoice) {
		invoiceRepo.delete(invoice);
	}
	
	
}
