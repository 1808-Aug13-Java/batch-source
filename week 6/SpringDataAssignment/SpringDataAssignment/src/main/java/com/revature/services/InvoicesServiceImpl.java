package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Invoices;
import com.revature.repositories.InvoicesRepository;

@Service
public class InvoicesServiceImpl implements InvoicesService {

	@Autowired
	InvoicesRepository invoiceRepo;
	
	public List<Invoices> findAllInvoices() {
		return invoiceRepo.findAll();
	}

	@Override
	public Invoices findInvoicesById(Long id) {
		return invoiceRepo.getOne(id);
	}

	@Override
	public Invoices addInvoices(Invoices newInvoice) {
		return invoiceRepo.save(newInvoice);
	}

	@Override
	public Invoices updateInvoices(Invoices invoice) {
		return invoiceRepo.save(invoice);
	}

	@Override
	public void deleteInvoices(Long id) {
		invoiceRepo.deleteById(id);
		
	}

}
