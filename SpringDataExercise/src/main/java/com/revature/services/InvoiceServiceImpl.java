package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Invoice;
import com.revature.repositories.InvoiceRepository;

@Service
public class InvoiceServiceImpl implements InvoiceService {
	
	/** The database interface for invoices. */
	@Autowired
	InvoiceRepository invRepo;
	
	
	@Override
	public List<Invoice> getAllInvoices() {
		return invRepo.findAll();
	}

	@Override
	public Invoice getInvoiceById(int id) {
		// Attempt to locate and return the invoice by id. Return null if not 
		// found. 
		Optional<Invoice> opt = invRepo.findById(id);
		return opt.isPresent() ? opt.get() : null;
	}

	@Override
	public Invoice addInvoice(Invoice inv) {
		return invRepo.save(inv);
	}

	@Override
	public void updateInvoice(Invoice inv) {
		invRepo.save(inv);
	}

	@Override
	public void deleteInvoiceById(int id) {
		invRepo.deleteById(id);
	}

}
