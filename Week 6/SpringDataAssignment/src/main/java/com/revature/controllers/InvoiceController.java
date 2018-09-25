package com.revature.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Invoice;
import com.revature.services.InvoiceService;
import com.revature.services.InvoiceService;

@RestController
@RequestMapping ("/invoice")
public class InvoiceController {

	@Autowired
	InvoiceService invoiceService;

	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody
	public List<Invoice> findAllInvoices(){
		return invoiceService.findAllInvoices();
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody
	public Invoice findInvoiceById(@PathVariable("id") int id) {
		return invoiceService.findInvoiceById(id);
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody
	public Invoice addInvoice(@Valid @RequestBody Invoice u) {
		return invoiceService.addInvoice(u);
	}
	
	@PutMapping(value="/{id}", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody
	public Invoice updateInvoice(@Valid @RequestBody Invoice u, @PathVariable("id") int id) {
		return invoiceService.updateInvoice(u);
	}
	
	@DeleteMapping(value="/{id}",consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody
	public void deleteInvoice(@RequestBody Invoice u, @PathVariable("id") int id) {
		invoiceService.deleteInvoice(u);
	}
	
	@RequestMapping(method= {RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.TRACE, RequestMethod.PATCH})
	public Invoice metadataCar() {
		return null;
	}
}
