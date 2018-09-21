package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.revature.models.Invoice;
import com.revature.services.InvoiceService;

@RestController
@RequestMapping(value="/customers")
public class InvoiceController {

	
	@Autowired
	InvoiceService invoiceService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Invoice> getAllInvoices() {
		return invoiceService.getInvoices();
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Invoice getInvoiceById(@PathVariable("id") int id) {
		return invoiceService.getInvoiceById(id);
	}
	
	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Invoice createInvoice(@RequestBody Invoice i) {
		return invoiceService.createInvoice(i);
	}
	
	@PutMapping(produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Invoice updateInvoice(@RequestBody Invoice i) {
		return invoiceService.updateInvoice(i);
	}
	
	@DeleteMapping(produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Invoice deleteInvoice(@RequestBody Invoice i) {
		return invoiceService.deleteInvoice(i);
	}
}
