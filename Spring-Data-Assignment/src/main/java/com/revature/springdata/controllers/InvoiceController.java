package com.revature.springdata.controllers;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
import com.revature.springdata.models.Invoice;
import com.revature.springdata.repositories.InvoiceRepository;

@RestController
@RequestMapping(value="/invoice")
public class InvoiceController {
  @Autowired
  InvoiceRepository invoiceRepository;
  
  @GetMapping(value="/") 
  public Iterable<Invoice> findInvoices() {
    return invoiceRepository.findAll(); 
  } 
  @GetMapping(value="/{id}")
  public Invoice findInvoice(@PathVariable("id") Long id) {
    return invoiceRepository.findById(id).get();
  }
  @PutMapping(value="/{id}")
  public void updateInvoice(@RequestBody Invoice i, @RequestParam("id")  Long id) {
    Invoice currentI = invoiceRepository.findById(id).get();
    currentI.setCreated(i.getCreated());
    currentI.setAmount(i.getAmount());
    invoiceRepository.save(currentI);
  }
  @PostMapping(value="/")
  public void createInvoice(@RequestBody Invoice i) {
    invoiceRepository.save(i);
  }
  @DeleteMapping(value="/{id}")
  public void deleteInvoice(@PathVariable("id") Long id) {
    Invoice i = invoiceRepository.findById(id).get();
    invoiceRepository.delete(i);
  }  
}
