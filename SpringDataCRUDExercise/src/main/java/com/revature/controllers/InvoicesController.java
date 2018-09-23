package com.revature.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Invoices;
import com.revature.services.InvoiceService;

@RestController
@RequestMapping("/invoices")
public class InvoicesController {
	
	@Autowired
	InvoiceService invoiceService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Invoices> findAllInvoices(){
		return invoiceService.findAllInvoices();
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Invoices findInvoiceById(@PathVariable("id") Long id) {
		return invoiceService.findInvoiceById(id);
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Invoices addInvoice(@Valid @RequestBody Invoices i) {
		return invoiceService.addInvoice(i);
	}
	
	@DeleteMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Invoices deleteInvoice(@RequestBody Invoices i, @PathVariable("id") Long id) {
		i.setInvoiceId(id);
		return invoiceService.deleteInvoice(i);
	}

}
