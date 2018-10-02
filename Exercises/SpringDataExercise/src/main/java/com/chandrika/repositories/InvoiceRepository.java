package com.chandrika.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chandrika.models.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>{
	
}
