package com.revature.controllers;

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

import com.revature.models.Invoice;
import com.revature.services.InvoiceService;

@Controller
@RequestMapping("/invoices")
public class InvoiceController
{
	@Autowired
	InvoiceService invoiceServe;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Invoice> findAllInvoices()
	{
		return invoiceServe.findAllInvoices();
	}
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE, value="/{id}")
	@ResponseBody
	public Invoice findInvoice(@PathVariable Integer id)
	{
		return invoiceServe.findInvoiceById(id);
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Invoice addInvoice(@RequestBody Invoice u)
	{
		return invoiceServe.addInvoice(u);
	}
	
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Invoice updateInvoice(@RequestBody Invoice u)
	{
		return invoiceServe.updateInvoice(u);
	}
	
	@DeleteMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Invoice deleteInvoice(@RequestBody Invoice u)
	{
		return invoiceServe.deleteInvoice(u);
	}

}
