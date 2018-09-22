package com.revature.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Customers;
import com.revature.models.Invoices;
import com.revature.services.InvoicesService;

@RestController
@RequestMapping("/invoices")
public class InvoicesController {
	@Autowired
	InvoicesService invoiceService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Invoices> findAllInvoices() {
		return invoiceService.findAllInvoices();
	}

	@GetMapping(value= "/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Invoices findInvoicesById(@PathVariable("id") Long id) {
		return invoiceService.findInvoicesById(id);
	}

	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Invoices addInvoices(@Valid @RequestBody Invoices newInvoice) {
		return invoiceService.addInvoices(newInvoice);
	}

	@PutMapping(value="/{id}", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Invoices updateInvoices(@Valid @RequestBody Invoices invoice, @PathVariable("id") Long id ) {
		invoice.setInvid(id);
		return invoiceService.updateInvoices(invoice);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public void deleteInvoices(@PathVariable("id") Long id) {
		invoiceService.deleteInvoices(id);
		
	}
	
	
	@RequestMapping(method= {RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.TRACE, RequestMethod.PATCH})
	public Invoices metadataCar() {
		return null;
	}

}
