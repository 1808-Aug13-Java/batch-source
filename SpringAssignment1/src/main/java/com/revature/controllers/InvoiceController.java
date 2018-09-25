package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.CustomerHibernate;
import com.revature.models.InvoiceHibernate;
import com.revature.services.InvoiceService;

@RestController
@RequestMapping(value="/invoice")
public class InvoiceController {
	@Autowired
	InvoiceService invoiceService;
	
	@GetMapping(produces="application/json")
	public List<InvoiceHibernate> listAllInvoices()
	{
		return invoiceService.findAllInvoices();
	}
	
	//get customer by id
	@GetMapping(value="/{id}", produces="application/json")
	public InvoiceHibernate getInvoiceById(@PathVariable("id") Long id)
	{
		return invoiceService.findInvoiceById(id);
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public InvoiceHibernate createInvoice(@RequestBody InvoiceHibernate invoice) 
	{
		return invoiceService.saveInvoice(invoice);
	}
	
	//update customer
	@PutMapping(consumes="application/json", produces="application/json")
	public InvoiceHibernate updateInvoice(@RequestBody InvoiceHibernate inv) 
	{
		return invoiceService.updateInvoice(inv);
	}
	//delete customer
	@DeleteMapping(consumes="application/json", produces=MediaType.TEXT_PLAIN_VALUE)
	public String deleteInvoice(@RequestBody InvoiceHibernate inv)
	{
		invoiceService.deleteInvoice(inv);
		return "Successfully deleted the invoice!";
	}
	
}
