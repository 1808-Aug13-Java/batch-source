package com.revature.springdata.repositories;

import com.revature.springdata.models.Invoice;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
  
}

