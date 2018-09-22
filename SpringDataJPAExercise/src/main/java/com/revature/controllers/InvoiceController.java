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
@RequestMapping("/invoice")
public class InvoiceController {

	@Autowired
	InvoiceService InvoiceServ;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Invoice> findAllInvoices(){
		return InvoiceServ.findAllInvoices();
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Invoice findInvoiceById(@PathVariable("id") Integer id) {
		return InvoiceServ.findInvoiceById(id);
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public Invoice addInvoice(@RequestBody Invoice c) {
		return InvoiceServ.addInvoice(c);
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public Invoice updateInvoice(@RequestBody Invoice c) {
		return InvoiceServ.updateInvoice(c);
	}
	
	@DeleteMapping(consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public Invoice deleteInvoice(@RequestBody Invoice c) {
		return InvoiceServ.deleteInvoice(c);
	}
	
	@DeleteMapping(value="/{id}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public Invoice deleteInvoiceById(@PathVariable("id") Integer id) {
		return InvoiceServ.deleteInvoiceById(id);
	}
}
