package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Invoice;
import com.revature.services.InvoiceService;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
	
	@Autowired
	InvoiceService invoiceServ;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Invoice> findAllCustomers(){
		return invoiceServ.findAllInvoices();
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Invoice findCustomerById(@PathVariable("id") int invoiceId) {
		return invoiceServ.getInvoiceById(invoiceId);
	}

}
