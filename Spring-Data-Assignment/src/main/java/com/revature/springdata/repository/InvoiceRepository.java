package com.revature.springdata.repository;

import com.revature.springdata.models.Invoice;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
  
}

