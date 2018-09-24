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

import com.revature.models.Invoice;
import com.revature.services.InvoiceService;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
	
	@Autowired
	InvoiceService invServe;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Invoice> getInvoices() {
		return invServe.getAllInvoices();
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Invoice getInvoiceById(@PathVariable("id") int invId) {
		return invServe.getInvoiceById(invId);
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Invoice addInvoice(@RequestBody Invoice inv) {
		return invServe.addInvoice(inv);
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public void updateInvoice(@RequestBody Invoice inv) {
		invServe.updateInvoice(inv);
	}
	
	@DeleteMapping(value="/{id}")
	public void deleteInvoice(@PathVariable("id") int invId) {
		invServe.deleteInvoiceById(invId);
	}
	
}