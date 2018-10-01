package com.chandrika.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chandrika.models.Invoice;
import com.chandrika.services.InvoiceService;

@Controller
@RequestMapping("/invoices")
public class InvoiceController {
	@Autowired
	InvoiceService invoiceService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Invoice> findAllInvoices() {
		return invoiceService.getAllInvoices();
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Invoice findInvoiceById(@PathVariable("id") Long id) {
		return invoiceService.getInvoiceById(id);
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Invoice addInvoice(@RequestBody Invoice i) {
		return invoiceService.addInvoice(i);
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Invoice updateInvoice(@RequestBody Invoice i) {
		return invoiceService.updateInvoice(i);
	}
	
	@DeleteMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Invoice deleteInvoice(@RequestBody Invoice i) {
		return invoiceService.deleteCustomer(i);
	}
}
