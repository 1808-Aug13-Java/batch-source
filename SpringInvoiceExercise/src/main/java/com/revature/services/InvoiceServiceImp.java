package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Invoice;
import com.revature.repositories.InvoiceRepository;

@Service
public class InvoiceServiceImp implements InvoiceService 
{
	@Autowired
	InvoiceRepository inRepo;
	
	@Override
	public List<Invoice> findAllInvoices() 
	{
		return inRepo.findAll();
	}

	@Override
	public Invoice findInvoiceById(Integer id)
	{
		return inRepo.getOne(id);
	}

	@Override
	public Invoice addInvoice(Invoice newInvoice) 
	{
		return inRepo.save(newInvoice);
	}

	@Override
	public Invoice updateInvoice(Invoice u)
	{
		return inRepo.save(u);
	}

	@Override
	public Invoice deleteInvoice(Invoice u) 
	{
		inRepo.delete(u);
		return u;
	}

}
