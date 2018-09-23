package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.models.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>{

}
